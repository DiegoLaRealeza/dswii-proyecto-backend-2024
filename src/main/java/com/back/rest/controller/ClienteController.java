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

import com.back.rest.dto.ClienteDTO;
import com.back.rest.entity.Cliente;
import com.back.rest.servicesImpl.ClienteServices;

import com.back.rest.utils.MensajeResponse;
import com.back.rest.utils.ModeloNotFoundException;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteServices servicioCli;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		List<Cliente>lista=servicioCli.listarTodos();
		if(lista==null) {
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("No hay registros")
					.object(null).build(),HttpStatus.OK);
		}
		else {
			List<ClienteDTO> data=lista.stream().map(m->   
				mapper.map(m, ClienteDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("registros")
					.object(data).build(),HttpStatus.OK);		
		}
		
	}
	
	@GetMapping("/consulta/{codigo}")
	public ResponseEntity<?> consulta(@PathVariable Integer codigo) {

		Cliente medBuscar=servicioCli.buscarPorID(codigo);

		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del cliente : "+codigo+" no existe");
		else {
			ClienteDTO dto=mapper.map(medBuscar, ClienteDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro existe")
					.object(dto).build(),HttpStatus.OK);
		}
	}
	@PostMapping("/grabar")
	public  ResponseEntity<?>  grabar(@Valid @RequestBody ClienteDTO bean){
		try {
			Cliente med=mapper.map(bean, Cliente.class);
			Cliente m=servicioCli.registrar(med);
			ClienteDTO dto=mapper.map(m, ClienteDTO.class);
			
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
	public ResponseEntity<?> actualizar(@Valid @RequestBody ClienteDTO bean){
		Cliente medBuscar=servicioCli.buscarPorID(bean.getIdCliente());
		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del cliente : "+bean.getIdCliente()+" no existe");
		else {
			Cliente med=mapper.map(bean, Cliente.class);
			Cliente m=servicioCli.actualizar(med);
			ClienteDTO dto=mapper.map(m, ClienteDTO.class);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Registro actualizado")
					.object(dto).build(),HttpStatus.OK);
		}
		
	}
	@DeleteMapping("/eliminar/{codigo}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer codigo) {
		Cliente medBuscar=servicioCli.buscarPorID(codigo);
		if(medBuscar==null)
			throw new ModeloNotFoundException("Còdigo del cliente : "+codigo+" no existe");
		else 
			servicioCli.eliminar(codigo);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
