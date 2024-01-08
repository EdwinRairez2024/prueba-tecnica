package com.prueba.pruebatecnica.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.prueba.pruebatecnica.models.entity.HistoryCity;

public interface IHistoryCityDao extends JpaRepository<HistoryCity, Long>{
	 List<HistoryCity> findFirst10ByOrderByIdDesc();
	 
	 HistoryCity findFirst1ByOrderByIdDesc();
}
