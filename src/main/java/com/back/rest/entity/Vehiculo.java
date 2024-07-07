package com.back.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="Vehiculo")
public class Vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVehiculo;
	private String placa ;
	private String marca; 
	private String modelo ;
	@Column(name = "anio")
	private Integer anio; 
	private String color;
    
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente ;

}
