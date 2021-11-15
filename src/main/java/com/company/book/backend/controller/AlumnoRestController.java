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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.book.backend.model.Alumno;
import com.company.book.backend.model.Categoria;
import com.company.book.backend.respose.AlumnoResponseRest;
import com.company.book.backend.respose.CategoriaResponseRest;
import com.company.book.backend.service.IAlumnoService;

@RestController
@RequestMapping("/v1")
public class AlumnoRestController {
	
	@Autowired
	private IAlumnoService service;
	
	@GetMapping("/alumnos/{id}")
	public ResponseEntity<AlumnoResponseRest> consultaAluporId(@PathVariable Long id) {
		   ResponseEntity<AlumnoResponseRest> response =  service.buscarPorId(id);
		return response;
	}
	
	@GetMapping("/alumnos")
	public ResponseEntity<AlumnoResponseRest> consultaAlu() {
		   ResponseEntity<AlumnoResponseRest> response =  service.buscarAlumnos();
		return response;
	}
	
	@PostMapping("/alumnos")
	public ResponseEntity<AlumnoResponseRest> crear(@RequestBody Alumno request){
		ResponseEntity<AlumnoResponseRest> response = service.crear(request);
		return response;
	}
	
	@PutMapping("/alumnos/{id}")
	public ResponseEntity<AlumnoResponseRest> actualizar(@RequestBody Alumno request, @PathVariable Long id){
		ResponseEntity<AlumnoResponseRest> response = service.modificar(request, id);
		return response;
	}
	
	@DeleteMapping("/alumnos/{id}")
	public ResponseEntity<AlumnoResponseRest> eliminar (@PathVariable Long id){
		ResponseEntity<AlumnoResponseRest> response = service.eliminar(id);
		return response;
	}

}
