package com.prueba.pruebatecnica.models.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.pruebatecnica.models.dao.IHistoryCityDao;
import com.prueba.pruebatecnica.models.entity.HistoryCity;
import com.prueba.pruebatecnica.models.services.IHistoryCityService;

@Service
public class HistoryCityServiceImpl implements IHistoryCityService{
	
	@Autowired
	private IHistoryCityDao historyCityDao;

		
	public List<HistoryCity> listHistoryCity() {
		return  historyCityDao.findFirst10ByOrderByIdDesc();
	}
	public HistoryCity city() {
		return  historyCityDao.findFirst1ByOrderByIdDesc();
	}

	@Override
	@Transactional
	public HistoryCity save(HistoryCity historyCity) {		 
		return historyCityDao.save(historyCity);
	}

}
