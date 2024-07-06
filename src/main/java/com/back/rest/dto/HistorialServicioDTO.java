package com.back.rest.dto;

import java.util.Date;

import com.back.rest.entity.Servicio;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HistorialServicioDTO {
	
	private Integer idHistorialServicio;
	
	@NotNull(message="{servicio.null}")
	private Servicio servicio;
	
	@NotNull(message="{fechaH.null}")
	private Date fecha;
	
	@NotNull(message="{descripcionH.null}")
	private String descripcion;

}
