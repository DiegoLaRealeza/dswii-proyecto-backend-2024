package com.back.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.rest.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
