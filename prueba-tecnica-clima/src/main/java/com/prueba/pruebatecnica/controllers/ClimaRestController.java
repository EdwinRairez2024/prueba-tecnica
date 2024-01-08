package com.prueba.pruebatecnica.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prueba.pruebatecnica.models.entity.HistoryCity;
import com.prueba.pruebatecnica.models.services.IHistoryCityService;
import com.sun.org.apache.xml.internal.utils.URI;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClimaRestController {
	@Autowired
	private IHistoryCityService  historyCityService;
	
	@GetMapping("/clima/{ciudad}")
	public ResponseEntity<?> climaCity(@PathVariable String ciudad){
				
		Map<String,Object> response = new HashMap<>();
		RestTemplate restTemplate=new RestTemplate();
		HistoryCity historyCity=new HistoryCity();
		
		
		ResponseEntity<String> responseEntity = null;
		String url="https://api.openweathermap.org/data/2.5/weather?q="+ciudad+"&lang=es&appid=e079aeda2dfe54acacd0ec20777eb1b5";		
		try {
			responseEntity= restTemplate.getForEntity(url, String.class);			
			if(responseEntity.getBody().length() > 0 ) {
				historyCity.setResultado(responseEntity.getBody());
				historyCityService.save(historyCity);
				response.put("Clima",responseEntity.getBody());			
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);	
				
			}
		} catch (Exception e) {
			HistoryCity historyCity1 = historyCityService.city();
			if(historyCity1!=null) {
				response.put("Clima",historyCity1.getResultado());	
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.METHOD_NOT_ALLOWED);
			}
			response.put("Error","La API de OpenWeather no se encuentra en uso, tampoco se tienen resultados de busquedas anteriores");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
			
		}
		
		
	
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);				
}
	
	@GetMapping("/clima/history")
	public ResponseEntity<?> climaHistory(){
		Map<String,Object> response = new HashMap<>();
		List<HistoryCity> historyCity= historyCityService.listHistoryCity();
		if(historyCity.isEmpty()) {
			response.put("Nota: ", "No se encontraron consultas del clima");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.METHOD_NOT_ALLOWED);
		}
		response.put("History ultimos 10", historyCity);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/clima/city")
	public HistoryCity ciy() {
		return historyCityService.city();
	}
	
	
	
}
