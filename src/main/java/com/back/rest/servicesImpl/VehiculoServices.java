package com.back.rest.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.back.rest.entity.Vehiculo;
import com.back.rest.repository.VehiculoRepository;

@Service
public class VehiculoServices extends ICRUDImpl<Vehiculo, Integer> {

	@Autowired
	private VehiculoRepository repo;
	@Override
	public JpaRepository<Vehiculo, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

	
}
