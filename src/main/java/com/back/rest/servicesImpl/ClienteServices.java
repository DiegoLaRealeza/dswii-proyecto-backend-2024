package com.back.rest.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.back.rest.entity.Cliente;
import com.back.rest.repository.ClienteRepository;

@Service
public class ClienteServices extends ICRUDImpl<Cliente, Integer>{

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public JpaRepository<Cliente, Integer> getRepository() {
		
		return repo;
	}

	
}
