package com.clasejava.microservicios.app.commons.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.clasejava.microservicios.app.commons.service.CommonService;


public class CommonController<E, S extends CommonService<E>> {
	
	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable(name="id") Long id){
		
		Optional<E> o =  service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody E alumno){
		
		E alumnoDb = service.save(alumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar( @PathVariable(name="id") Long id){
		
	   service.deleteById(id);
	   return ResponseEntity.noContent().build();
		
	}

}
