package com.back.rest.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.back.rest.entity.Servicio;
import com.back.rest.repository.ServicioRepository;

@Service
public class ServicioServices extends ICRUDImpl<Servicio, Integer> {

	@Autowired
	private ServicioRepository repo;
	@Override
	public JpaRepository<Servicio, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

}
