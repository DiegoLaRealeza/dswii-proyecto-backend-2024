package com.back.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.rest.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

}
