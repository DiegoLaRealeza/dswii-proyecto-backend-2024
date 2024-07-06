package com.back.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClienteDTO {
	
	private Integer idCliente;
	
	@NotNull( message="{nombre.null}")
	private String nombre;
	
	@NotNull( message ="{apellido.null}")
	private String apellido;
	
	@NotNull( message ="{dni.null}")
	@Pattern(regexp = "\\d{8}", message = "{dni.val}")
	private String dni;
	
	@NotNull( message ="{direccion.null}")
	private String direccion; 
	
	@NotNull( message ="{telefono.null}")
	@Pattern(regexp = "^9\\d{8}$", message = "{telefono.val}")
	private String telefono;
	
	@NotNull( message ="{correo.null}")
	private String correo;
}
