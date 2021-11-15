package com.company.book.backend.service;

import org.springframework.http.ResponseEntity;

import com.company.book.backend.model.Categoria;
import com.company.book.backend.respose.CategoriaResponseRest;

public interface ICategoriaService {
	public ResponseEntity<CategoriaResponseRest> buscarCategorias();
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);
	public ResponseEntity<CategoriaResponseRest> modificar(Categoria categoria, Long id);
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id);
}
