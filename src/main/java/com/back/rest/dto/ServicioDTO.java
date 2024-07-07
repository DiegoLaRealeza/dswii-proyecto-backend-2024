package com.back.rest.dto;

import java.util.Date;

import com.back.rest.entity.Vehiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ServicioDTO {
	private Integer idServicio;

	@NotNull(message="{descripcionS.null}")
	private String descripcion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd - HH:mm")
	@PastOrPresent(message="{fechaS.val}")
	private Date fecha;
	
	@NotNull(message="{costo.null}")
	@Digits(integer = 10, fraction = 2, message = "{costo.dig}")
    @Positive(message = "{costo.val}")
	private Double costo;
	
	@Min(value = 0, message = "{estado.min}")
    @Max(value = 1, message = "{estado.max}")
	private Integer estado;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private VehiculoDTO vehiculo;
}
