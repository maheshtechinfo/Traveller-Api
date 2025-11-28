package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.ITravellerMgmtService;
import com.nt.vo.TravellerVO;

@RestController
@RequestMapping("/traveller-api")
public class TravellerOperaionsRestController {

	@Autowired
	private ITravellerMgmtService travellerService;

	/*	@PostMapping("/save") // endPoint1
		public ResponseEntity<String> registerTraveller(@RequestBody TravellerVO vo) {
			try {
				// use service
				String msg = travellerService.registerTraveller(vo);
				return new ResponseEntity<String>(msg, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
		@PostMapping("/save-group") // endpoint2
		public ResponseEntity<String> registerGroupTravellers(@RequestBody List<TravellerVO> listVo) {
			try {
				// use service
				String msg = travellerService.registerGroupTravellers(listVo);
				return new ResponseEntity<String>(msg, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
		@GetMapping("/findAll") // endpoint3
		public ResponseEntity<?> displayAllTravellers() {
			try {
				// use service
				List<TravellerVO> listVO = travellerService.showAllTravellers();
				return new ResponseEntity<List<TravellerVO>>(listVO, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
		@GetMapping("/find/{id}") //endpoint4
		public ResponseEntity<Object> showTravellerById(@PathVariable Integer id) {
			try {
				// use service
				TravellerVO vo = travellerService.showTravellerById(id);
				return new ResponseEntity<Object>(vo, HttpStatus.OK);
			} // try
			catch (Exception e) {
				return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}*/

	@PostMapping("/save") // endPoint1
	public ResponseEntity<String> registerTraveller(@RequestBody TravellerVO vo) {
		// use service
		String msg = travellerService.registerTraveller(vo);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@PostMapping("/save-group") // endpoint2
	public ResponseEntity<String> registerGroupTravellers(@RequestBody List<TravellerVO> listVo) {
		// use service
		String msg = travellerService.registerGroupTravellers(listVo);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@GetMapping("/findAll") // endpoint3
	public ResponseEntity<List<TravellerVO>> displayAllTravellers() {
		// use service
		List<TravellerVO> listVO = travellerService.showAllTravellers();
		return new ResponseEntity<List<TravellerVO>>(listVO, HttpStatus.OK);
	}

	@GetMapping("/find/{id}") // endpoint4
	public ResponseEntity<TravellerVO> showTravellerById(@PathVariable Integer id) {
		// use service
		TravellerVO vo = travellerService.showTravellerById(id);
		return new ResponseEntity<TravellerVO>(vo, HttpStatus.OK);
	}

	@GetMapping("/find/{country1}/{country2}/{country3}")
	public ResponseEntity<List<TravellerVO>> showTravellerById(@PathVariable String country1,
			@PathVariable String country2, @PathVariable String country3) {
		// use service
		List<TravellerVO> listVO = travellerService.showTravellersByCountries(List.of(country1, country2, country3));
		return new ResponseEntity<List<TravellerVO>>(listVO, HttpStatus.OK);
	}

	@PatchMapping("/update/{currentLocation}/{headingTo}")
	public ResponseEntity<String> updateHeadingToByCurentLocation(@PathVariable String currentLocation,
			@PathVariable String headingTo) {
		// use service
		String msg = travellerService.updateHeadingToByCurrentLocation(currentLocation, headingTo);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> modifyTraveller(@RequestBody TravellerVO vo) {
		// use Service
		String msg = travellerService.updateTraveller(vo);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeTravellerById(@PathVariable int id) {
		// use service
		String msg = travellerService.deleteTravellerById(id);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/removeAll/{countries}")
	public ResponseEntity<String> removeTravellersCountries(@PathVariable List<String> countries) {
		System.out.println("List data ::" + countries);
		// use service
		String msg = travellerService.deleteTravellersByCoutries(countries);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
