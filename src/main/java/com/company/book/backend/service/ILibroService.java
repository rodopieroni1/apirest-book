package com.company.book.backend.service;

import org.springframework.http.ResponseEntity;
import com.company.book.backend.respose.LibrosResponseRest;
import com.company.book.backend.model.*;

public interface ILibroService {
	public ResponseEntity<LibrosResponseRest> buscarLibros();
	public ResponseEntity<LibrosResponseRest> buscarPorId(Long id);
	public ResponseEntity<LibrosResponseRest> crear(Libro libros);
	public ResponseEntity<LibrosResponseRest> modificar(Libro libros, Long id);
	public ResponseEntity<LibrosResponseRest> eliminar(Long id);
}
