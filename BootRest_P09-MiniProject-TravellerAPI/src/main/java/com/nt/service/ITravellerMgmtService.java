package com.nt.service;

import java.util.List;

import com.nt.vo.TravellerVO;

public interface ITravellerMgmtService {
	public String registerTraveller(TravellerVO vo);

	public String registerGroupTravellers(List<TravellerVO> listVO);

	public List<TravellerVO> showAllTravellers();

	public TravellerVO showTravellerById(int id);

	public List<TravellerVO> showTravellersByCountries(List<String> countries);

	public String updateHeadingToByCurrentLocation(String currentLocation, String newDestinaton);

	public String updateTraveller(TravellerVO vo);

	public String deleteTravellerById(int id);

	public String deleteTravellersByCoutries(List<String> countries);

}
