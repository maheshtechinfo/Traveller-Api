package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.TravellerEntity;
import com.nt.exception.TravellerNotFoundException;
import com.nt.repository.ITravellerRepository;
import com.nt.vo.TravellerVO;

import jakarta.transaction.Transactional;

@Service
public class TravellerMgmtServiceImpl implements ITravellerMgmtService {

	@Autowired
	ITravellerRepository travellerRepo;

	@Override
	public String registerTraveller(TravellerVO vo) {
		// Convert TravellerVo to TravellerEntity
		TravellerEntity entity = new TravellerEntity();
		BeanUtils.copyProperties(vo, entity);
		// set required data
		entity.setCreatedBy(System.getProperty("user.name"));
		// use repo
		Integer tIdValue = travellerRepo.save(entity).getTId();
		return "Traveller is saved with id value : " + tIdValue;
	}

	@Override
	public String registerGroupTravellers(List<TravellerVO> listVO) {
		// convert listVO to ListEntity
		List<TravellerEntity> listEntity = new ArrayList<TravellerEntity>();
		listVO.forEach(vo -> {
			TravellerEntity entity = new TravellerEntity();
			entity.setCreatedBy(System.getProperty("user.name"));
			BeanUtils.copyProperties(vo, entity);
			listEntity.add(entity);
		});
		// use repo
		List<TravellerEntity> savedListEntities = travellerRepo.saveAll(listEntity);
		List<Integer> listIds = savedListEntities.stream().map(TravellerEntity::getTId).toList();
		return "Travellers are saved with the id values ::" + listIds;

	}

	@Override
	public List<TravellerVO> showAllTravellers() {
		// use reo
		List<TravellerEntity> listEntity = travellerRepo.findAll();
		// convert listEntity to listVO
		List<TravellerVO> listVO = listEntity.stream().map(entity -> {
			TravellerVO vo = new TravellerVO();
			BeanUtils.copyProperties(entity, vo);
			return vo;
		}).collect(Collectors.toList());
		return listVO;
	}

	@Override
	public TravellerVO showTravellerById(int id) {
		// use Repo
		TravellerEntity entity = travellerRepo.findById(id)
				.orElseThrow(() -> new TravellerNotFoundException("Invalid Id"));
		// convert vo to entity
		TravellerVO vo = new TravellerVO();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<TravellerVO> showTravellersByCountries(List<String> listCounties) {
		// use repo
		List<TravellerEntity> listEntity = travellerRepo.fetchTravellersByCountries(listCounties);
		// convert listEntity to listVO
		List<TravellerVO> listVO = listEntity.stream().map(entity -> {
			TravellerVO vo = new TravellerVO();
			BeanUtils.copyProperties(entity, vo);
			return vo;
		}).collect(Collectors.toList());
		return listVO;

	}

	@Override
	public String updateHeadingToByCurrentLocation(String currentLocation, String newDestinaton) {
		// use Repo
		List<TravellerEntity> listEntity = travellerRepo.findByCurrentLocation(currentLocation);
		// update heading to
		listEntity.forEach(entity -> {
			entity.setHeadingTo(newDestinaton);
		});
		// update all
		travellerRepo.saveAll(listEntity);
		return listEntity.size() + " no.of  Travellers HEadingTo locaton is changed";
	}

	@Override
	public String updateTraveller(TravellerVO vo) {
		// findById
		TravellerEntity entity = travellerRepo.findById(vo.getTId())
				.orElseThrow(() -> new TravellerNotFoundException("Invalid id"));
		// copy new data replacing old data
		BeanUtils.copyProperties(vo, entity);
		// update the object
		travellerRepo.save(entity);
		return vo.getTId() + "  Traveller is updated";
	}

	@Override
	public String deleteTravellerById(int id) {
		// find by Id
		TravellerEntity entity = travellerRepo.findById(id)
				.orElseThrow(() -> new TravellerNotFoundException("invalid id"));
		// delete the Traveller
		travellerRepo.deleteById(id);
		return "Traveller Found and Deleted";
	}

	@Override
	@Transactional
	public String deleteTravellersByCoutries(List<String> countries) {
		// use repo
		int count = travellerRepo.deleteTravellersByCountries(countries);
		int count1 = travellerRepo.deleteOrphanTravellers();
		return count1 == 0 ? "No Travellers are found" : count1 + " no.of Travellers  are found and Deleted";
	}

}
