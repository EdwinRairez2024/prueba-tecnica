package com.prueba.pruebatecnica.models.services;

import java.util.List;

import com.prueba.pruebatecnica.models.entity.HistoryCity;

public interface IHistoryCityService {
	
	public List<HistoryCity> listHistoryCity();	
	public HistoryCity city();
	public HistoryCity save(HistoryCity historyCity);
}
