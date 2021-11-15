package com.company.book.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.book.backend.model.Categoria;
import com.company.book.backend.respose.CategoriaResponseRest;
import com.company.book.backend.service.ICategoriaService;

public class CategoriaRestControllerTest {

	@InjectMocks
	CategoriaRestController categoriaController;
	@Mock
	private ICategoriaService service;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void crearTest() {		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Categoria categoria = new Categoria(Long.valueOf(1), "Clasicos", "Libros clasicos de literatura");
		when(service.crear(any(Categoria.class))).thenReturn(new ResponseEntity<CategoriaResponseRest>(HttpStatus.OK));
		ResponseEntity<CategoriaResponseRest> respuesta= categoriaController.crear(categoria);		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);	}
	
	@Test
	public void actualizararTest() {
		
		/*MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	
		Categoria categoria = new Categoria(Long.valueOf(1), "Clasicos", "Libros clasicos de literatura");
		
		when(service.modificar(categoria), null).thenReturn(new ResponseEntity<CategoriaResponseRest>(HttpStatus.OK));
		
		ResponseEntity<CategoriaResponseRest> respuesta= categoriaController.actualizar(categoria,1);		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);*/
	}
	
}
