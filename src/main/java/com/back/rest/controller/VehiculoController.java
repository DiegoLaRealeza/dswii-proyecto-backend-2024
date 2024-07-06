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

import com.back.rest.dto.VehiculoDTO;
import com.back.rest.entity.Vehiculo;
import com.back.rest.servicesImpl.VehiculoServices;
import com.back.rest.utils.MensajeResponse;
import com.back.rest.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {
	@Autowired
	private VehiculoServices servicioVehi;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		List<Vehiculo>lista=servicioVehi.listarTodos();
		if(lista==null) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("No hay registros")
					.object(null).build(),HttpStatus.OK);
		}
		else {
			List<VehiculoDTO> data=lista.stream().map(m->   
				mapper.map(m, VehiculoDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("registros")
					.object(data).build(),HttpStatus.OK);		
		}
		
	}
	
	@GetMapping("/consulta/{codigo}")
	public ResponseEntity<?> consulta(@PathVariable Integer codigo) {

		Vehiculo medBuscar=servicioVehi.buscarPorID(codigo);

		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del vehiculo : "+codigo+" no existe");
		else {
			VehiculoDTO dto=mapper.map(medBuscar, VehiculoDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro existe")
					.object(dto).build(),HttpStatus.OK);
		}
	}
	@PostMapping("/grabar")
	public  ResponseEntity<?>  grabar(@Valid @RequestBody VehiculoDTO bean){
		try {
			Vehiculo med=mapper.map(bean, Vehiculo.class);
			Vehiculo m=servicioVehi.registrar(med);
			VehiculoDTO dto=mapper.map(m, VehiculoDTO.class);
			
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
	public ResponseEntity<?> actualizar(@Valid @RequestBody VehiculoDTO bean){
		Vehiculo medBuscar=servicioVehi.buscarPorID(bean.getIdVehiculo());
		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del vehiculo : "+bean.getIdVehiculo()+" no existe");
		else {
			Vehiculo med=mapper.map(bean, Vehiculo.class);
			Vehiculo m=servicioVehi.actualizar(med);
			VehiculoDTO dto=mapper.map(m, VehiculoDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro actualizado")
					.object(dto).build(),HttpStatus.OK);
		}
		
	}
	@DeleteMapping("/eliminar/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer codigo) {
		Vehiculo medBuscar=servicioVehi.buscarPorID(codigo);
		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del vehiculo : "+codigo+" no existe");
		else 
			servicioVehi.eliminar(codigo);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
