package com.company.book.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.book.backend.model.Alumno;
import com.company.book.backend.respose.AlumnoResponseRest;
import com.company.book.backend.respose.CategoriaResponseRest;

public interface IAlumnoService {
	public ResponseEntity<AlumnoResponseRest>buscarAlumnos();
	public ResponseEntity<AlumnoResponseRest>buscarPorId(Long id);
	public ResponseEntity<AlumnoResponseRest>crear(Alumno alumno);
	public ResponseEntity<AlumnoResponseRest>modificar(Alumno alumno, Long id);
	public ResponseEntity<AlumnoResponseRest>eliminar(Long id);
}
