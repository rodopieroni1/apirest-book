package com.company.book.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.book.backend.model.Libro;
import com.company.book.backend.respose.LibrosResponseRest;
import com.company.book.backend.service.ILibroService;

@RestController
@RequestMapping("/v1")
public class LibroRestController {
	
	@Autowired
	private ILibroService service;
	
	
	@GetMapping("/libros")
	public ResponseEntity<LibrosResponseRest> consultaLibros() {
		ResponseEntity<LibrosResponseRest> response =  service.buscarLibros();
		return response;
	}
	
	@GetMapping("/libros/{id}")
	public ResponseEntity<LibrosResponseRest> consultaPorID(@PathVariable Long id){
		ResponseEntity<LibrosResponseRest> response =  service.buscarPorId(id);
		return response;
	}
	@PostMapping("/libros")
	public ResponseEntity<LibrosResponseRest> crear(@RequestBody Libro request){
		ResponseEntity<LibrosResponseRest> response = service.crear(request);
		return response;
	}
	@PutMapping("/libros/{id}")
	public ResponseEntity<LibrosResponseRest> actualizar(@RequestBody Libro request,@PathVariable Long id){
		ResponseEntity<LibrosResponseRest> response = service.modificar(request, id);
		return response;
	}
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<LibrosResponseRest> eliminar(@PathVariable Long id){
		ResponseEntity<LibrosResponseRest> response = service.eliminar(id);
		return response;
	}

}
