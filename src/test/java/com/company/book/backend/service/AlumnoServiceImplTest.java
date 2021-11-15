package com.company.book.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.company.book.backend.model.Alumno;
import com.company.book.backend.model.dao.IAlumnoDao;
import com.company.book.backend.respose.AlumnoResponseRest;

public class AlumnoServiceImplTest {

	@InjectMocks
	AlumnoServiceImpl service;
	
	@Mock
	IAlumnoDao alumnoDao;
	
	List<Alumno> listAl = new ArrayList<Alumno>();
	private Alumno catUnoModificacion =new Alumno() ;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.cargarAlumno();
	}
	

	@Test
	public void modificarTest() {
		Optional<Alumno> catUno  = Optional.of(new Alumno(Long.valueOf(1), "Juan Peres",30145, "Lejos"));
		catUnoModificacion  = new Alumno(Long.valueOf(1), "Juan Modificacion",222, "Muy Lejos");
		when(alumnoDao.findById(Long.valueOf(1))).thenReturn(catUno);
		when(alumnoDao.save(catUno.get())).thenReturn(catUnoModificacion);
		ResponseEntity<AlumnoResponseRest> respuesta = service.modificar(catUnoModificacion,Long.valueOf(1));
		assertNotNull(respuesta);
		assertEquals("OK", respuesta.getStatusCode().name());
	}
	
	
	
	@Test
	public void crearTest(){
		Alumno catUno  = new Alumno(Long.valueOf(1), "Juan Peres",30145, "Lejos");
		when(alumnoDao.save(catUno)).thenReturn(catUno);		
		ResponseEntity<AlumnoResponseRest> response = service.crear(catUno);
		assertNotNull(response);
	}
	
	
	public void cargarAlumno() {
		Alumno catUno    = new Alumno(Long.valueOf(1), "Juan Peres",30145, "Lejos");
		Alumno catDos    = new Alumno(Long.valueOf(1), "Juan Gomez",30, "Tucuman");
		Alumno catTres   = new Alumno(Long.valueOf(1), "Juan Caceres",145, "Cordoba");
		Alumno catCuatro = new Alumno(Long.valueOf(1), "Juan Diaz",014, "San Luiz");
		
		listAl.add(catUno);
		listAl.add(catDos);
		listAl.add(catTres);
		listAl.add(catCuatro);
	}
}
