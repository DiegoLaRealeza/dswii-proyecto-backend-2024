package com.back.rest.dto;

import com.back.rest.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class VehiculoDTO {
	private Integer idVehiculo;
	@Pattern(regexp = "^[A-Za-z0-9]{3}-[A-Za-z0-9]{3}$", message = "{placa.val}")
	@NotNull(message ="{placa.null}")
	private String placa ;
	@NotNull(message ="{marca.null}")
	private String marca;
	@NotNull(message ="{modelo.null}")
	private String modelo ;
	@NotNull(message ="{anio.null}")
	private Integer anio; 
	@NotNull(message ="{color.null}")
	private String color;
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message="{cliente.null}")
    private Cliente cliente ;
}
