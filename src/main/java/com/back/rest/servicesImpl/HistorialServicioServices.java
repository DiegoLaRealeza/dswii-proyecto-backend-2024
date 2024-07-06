package com.back.rest.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.back.rest.entity.HistorialServicio;
import com.back.rest.repository.HistorialServicioRepository;

@Service
public class HistorialServicioServices extends ICRUDImpl<HistorialServicio, Integer> {

	@Autowired
	private HistorialServicioRepository repo;
	@Override
	public JpaRepository<HistorialServicio, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

}
