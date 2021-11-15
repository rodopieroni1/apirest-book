package com.company.book.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.book.backend.model.Categoria;
import com.company.book.backend.model.Libro;
import com.company.book.backend.model.dao.ILibroDao;
import com.company.book.backend.respose.LibrosResponseRest;

public class LibroServiceImplTest {
	
	
	@InjectMocks
	LibrosServiceImpl serviceLibros;
	@Mock
	ILibroDao libroDao;
	
	List<Libro> listLib = new ArrayList<Libro>();
	
	Categoria catId =new Categoria();
	
	Libro libroActualizada = new Libro();
	
	@BeforeEach
	public void init() {		
		MockitoAnnotations.openMocks(this);// se indica que se vana usar las anotaciones de mockito
		this.cargarLibro();
	}

	@Test
	public void buscarLibrosTest() {		
		when(libroDao.findAll()).thenReturn(listLib);
		ResponseEntity<LibrosResponseRest> response = serviceLibros.buscarLibros();
		assertEquals(4, response.getBody().getLibroResponse().getLibro().size());
		verify(libroDao, times(1)).findAll();		
	}
	
	@Test
	public void buscarPorIdTest() {
		Optional<Libro> catUno = Optional.of(new Libro(Long.valueOf(1), "Terror1", "terror 11", catId));		
		when(libroDao.findById(Long.valueOf(1))).thenReturn(catUno) ;		
		ResponseEntity<LibrosResponseRest> response = serviceLibros.buscarPorId(1L);		
		assertEquals(1,response.getBody().getLibroResponse().getLibro().size());		
		verify(libroDao, times(1)).findById(1L);
	}
	
	@Test
	public void modificarTest() {		
		Optional<Libro> catUno    = Optional.of(new Libro(Long.valueOf(1), "Terror1", "terror 11", catId));		
		libroActualizada = new Libro(Long.valueOf(1), "Nombre5", "Descripcion5", catId);		
		when(libroDao.findById(Long.valueOf(1))).thenReturn(catUno);
		when(libroDao.save(catUno.get())).thenReturn(libroActualizada);
		ResponseEntity<LibrosResponseRest> response1 = serviceLibros.modificar(libroActualizada, 1L);
		assertNotNull(response1);
		assertEquals("OK", response1.getStatusCode().name());
	}
	
	@Test
	public void eliminarTest() {
		doNothing().when(libroDao).deleteById(1L);
		ResponseEntity<LibrosResponseRest> response1 = serviceLibros.eliminar(1L);
		assertNotNull(response1);
		assertEquals("OK", response1.getStatusCode().name());
		verify(libroDao, times(1)).deleteById(1L);
	}
	
	public void cargarLibro() {
		Libro catUno    = new Libro(Long.valueOf(1), "Terror1", "terror 11", catId);
		Libro catDos    = new Libro(Long.valueOf(1), "Terror2", "terror 12", catId);
		Libro catTres   = new Libro(Long.valueOf(1), "Terror3", "terror 13", catId);
		Libro catCuatro = new Libro(Long.valueOf(1), "Terror4", "terror 14", catId);
		
		listLib.add(catUno);
		listLib.add(catDos);
		listLib.add(catTres);
		listLib.add(catCuatro);
	}
}
