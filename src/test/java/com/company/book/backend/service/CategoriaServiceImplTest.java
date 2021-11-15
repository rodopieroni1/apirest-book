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
import com.company.book.backend.model.dao.ICategoriaDao;
import com.company.book.backend.respose.CategoriaResponseRest;

public class CategoriaServiceImplTest {

	@InjectMocks
	CategoriaServiceImpl service;
	@Mock
	ICategoriaDao categoriaDao;	
	
	List<Categoria> listCat = new ArrayList<Categoria>();
	Categoria categoriaActualizada;
	
	@BeforeEach
	public void init() {		
		MockitoAnnotations.openMocks(this);// se indica que se vana usar las anotaciones de mockito
		this.cargarCategoria();
	}
	
	@Test
	public void buscarCategoriasTest() {
		when(categoriaDao.findAll()).thenReturn(listCat);
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		verify(categoriaDao, times(1)).findAll();
	} 
	
	@Test
	public void buscarPorIdTest() {
		Optional<Categoria> catUno = Optional.of(new Categoria(Long.valueOf(1), "Nombre1", "Descripcion1"));		
		when(categoriaDao.findById(Long.valueOf(1))).thenReturn(catUno) ;		
		ResponseEntity<CategoriaResponseRest> response = service.buscarPorId(1L);		
		assertEquals(1,response.getBody().getCategoriaResponse().getCategoria().size());		
		verify(categoriaDao, times(1)).findById(1L);
	}
	
	@Test
	public void modificarTest() {		
		Optional<Categoria> catUno    = Optional.of(new Categoria(Long.valueOf(1), "Nombre1", "Descripcion1"));		
		categoriaActualizada = new Categoria(Long.valueOf(1), "Nombre5", "Descripcion5");		
		when(categoriaDao.findById(Long.valueOf(1))).thenReturn(catUno) ;		
		when(categoriaDao.save(catUno.get())).thenReturn(categoriaActualizada);
		ResponseEntity<CategoriaResponseRest> response1 = service.modificar(categoriaActualizada, 1L);
		assertNotNull(response1);
		assertEquals("OK", response1.getStatusCode().name());
		
	}
	
	@Test
	public void eliminarTest() {
		doNothing().when(categoriaDao).deleteById(Long.valueOf(1));	
		ResponseEntity<CategoriaResponseRest> response = service.eliminar(Long.valueOf(1));		
		assertEquals("OK", response.getStatusCode().name());	
		verify(categoriaDao, times(1)).deleteById(1L);
	}
	
	
	
	public void cargarCategoria() {
		Categoria catUno    = new Categoria(Long.valueOf(1), "Nombre1", "Descripcion1");
		Categoria catDos    = new Categoria(Long.valueOf(1), "Nombre2", "Descripcion2");
		Categoria catTres   = new Categoria(Long.valueOf(1), "Nombre3", "Descripcion3");
		Categoria catCuatro = new Categoria(Long.valueOf(1), "Nombre4", "Descripcion4");
		
		listCat.add(catUno);
		listCat.add(catDos);
		listCat.add(catTres);
		listCat.add(catCuatro);
		
	}
	
	
}
