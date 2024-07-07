package com.back.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.rest.dto.ServicioDTO;
import com.back.rest.dto.VehiculoDTO;
import com.back.rest.entity.Servicio;
import com.back.rest.entity.Vehiculo;
import com.back.rest.servicesImpl.ServicioServices;
import com.back.rest.utils.MensajeResponse;
import com.back.rest.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/servicio")
public class ServicioController {
	
	@Autowired
	private ServicioServices servicioSer;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		List<Servicio>lista=servicioSer.listarTodos();
		if(lista==null) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("No hay registros")
					.object(null).build(),HttpStatus.OK);
		}
		else {
			List<ServicioDTO> data=lista.stream().map(m->   
				mapper.map(m, ServicioDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("registros")
					.object(data).build(),HttpStatus.OK);		
		}
		
	}
	
	@GetMapping("/consulta/{codigo}")
	public ResponseEntity<?> consulta(@PathVariable Integer codigo) {

		Servicio medBuscar=servicioSer.buscarPorID(codigo);

		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del servicio : "+codigo+" no existe");
		else {
			ServicioDTO dto=mapper.map(medBuscar, ServicioDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro existe")
					.object(dto).build(),HttpStatus.OK);
		}
	}
	@PostMapping("/grabar")
	public  ResponseEntity<?>  grabar(@Valid @RequestBody ServicioDTO bean){
		try {
			Servicio med=mapper.map(bean, Servicio.class);
			med.setEstado(1);
			med.setFecha(new Date());
			Servicio m=servicioSer.registrar(med);
			ServicioDTO dto=mapper.map(m, ServicioDTO.class);
			
			return new ResponseEntity<>(MensajeResponse.builder()
						.mensaje("Guardado correctamente")
						.object(dto).build(),HttpStatus.CREATED);	
		} catch (Exception e) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje(e.getMessage())
					.object(null).build(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizar(@Valid @RequestBody ServicioDTO bean){
		Servicio medBuscar=servicioSer.buscarPorID(bean.getIdServicio());
		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del servicio : "+bean.getIdServicio()+" no existe");
		else {
			Servicio med=mapper.map(bean, Servicio.class);
			Servicio m=servicioSer.actualizar(med);
			ServicioDTO dto=mapper.map(m, ServicioDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro actualizado")
					.object(dto).build(),HttpStatus.OK);
		}
		
	}
	@DeleteMapping("/eliminar/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer codigo) {
		Servicio medBuscar=servicioSer.buscarPorID(codigo);
		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del servicio : "+codigo+" no existe");
		else 
			servicioSer.eliminar(codigo);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
