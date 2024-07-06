package com.back.rest.controller;

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

import com.back.rest.dto.HistorialServicioDTO;
import com.back.rest.entity.HistorialServicio;
import com.back.rest.servicesImpl.HistorialServicioServices;
import com.back.rest.utils.MensajeResponse;
import com.back.rest.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/historial")
public class HistorialServicioController {
	@Autowired
	private HistorialServicioServices servicioHis;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		List<HistorialServicio>lista=servicioHis.listarTodos();
		if(lista==null) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("No hay registros")
					.object(null).build(),HttpStatus.OK);
		}
		else {
			List<HistorialServicioDTO> data=lista.stream().map(m->   
				mapper.map(m, HistorialServicioDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("registros")
					.object(data).build(),HttpStatus.OK);		
		}
		
	}
	
	@GetMapping("/consulta/{codigo}")
	public ResponseEntity<?> consulta(@PathVariable Integer codigo) {

		HistorialServicio medBuscar=servicioHis.buscarPorID(codigo);

		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del historial servicio : "+codigo+" no existe");
		else {
			HistorialServicioDTO dto=mapper.map(medBuscar, HistorialServicioDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro existe")
					.object(dto).build(),HttpStatus.OK);
		}
	}
	@PostMapping("/grabar")
	public  ResponseEntity<?>  grabar(@Valid @RequestBody HistorialServicioDTO bean){
		try {
			HistorialServicio med=mapper.map(bean, HistorialServicio.class);
			HistorialServicio m=servicioHis.registrar(med);
			HistorialServicioDTO dto=mapper.map(m, HistorialServicioDTO.class);
			
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
	public ResponseEntity<?> actualizar(@Valid @RequestBody HistorialServicioDTO bean){
		HistorialServicio medBuscar=servicioHis.buscarPorID(bean.getIdHistorialServicio());
		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del historial servicio : "+bean.getIdHistorialServicio()+" no existe");
		else {
			HistorialServicio med=mapper.map(bean, HistorialServicio.class);
			HistorialServicio m=servicioHis.actualizar(med);
			HistorialServicioDTO dto=mapper.map(m, HistorialServicioDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro actualizado")
					.object(dto).build(),HttpStatus.OK);
		}
		
	}
	@DeleteMapping("/eliminar/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer codigo) {
		HistorialServicio medBuscar=servicioHis.buscarPorID(codigo);
		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del historial servicio : "+codigo+" no existe");
		else 
			servicioHis.eliminar(codigo);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
