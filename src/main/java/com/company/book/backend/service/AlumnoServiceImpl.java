package com.company.book.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.book.backend.model.Alumno;
import com.company.book.backend.model.Categoria;
import com.company.book.backend.model.dao.IAlumnoDao;
import com.company.book.backend.respose.AlumnoResponseRest;
import com.company.book.backend.respose.CategoriaResponseRest;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	private static final Logger log = LoggerFactory.getLogger(AlumnoServiceImpl.class);

	@Autowired
	IAlumnoDao AlumnoDao;

	@Override
	public ResponseEntity<AlumnoResponseRest> buscarPorId(Long id) {
		log.info("Inicio metodo Crear Alumnos");
		AlumnoResponseRest response = new AlumnoResponseRest();
		List<Alumno> list = new ArrayList<>();
		try {
			Optional<Alumno> alumnoGuardado = AlumnoDao.findById(id);
			if (alumnoGuardado.isPresent()) {
				list.add(alumnoGuardado.get());
				response.getAlumnoResponse().setAlumnos(list);
			} else {
				log.error("Error en consultar Alumno");
				response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
				return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en consultar Alumno");
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional // (readOnly = true)
	public ResponseEntity<AlumnoResponseRest> buscarAlumnos() {
		log.info("Inicio del metodo buscarCategoria()");
		AlumnoResponseRest response = new AlumnoResponseRest();
		try {
			List<Alumno> alumno = (List<Alumno>) AlumnoDao.findAll();
			response.getAlumnoResponse().setAlumnos(alumno);
			response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			log.error("Error al consultar Alumnos: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// devuelve
		}
		return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.OK);// devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<AlumnoResponseRest> crear(Alumno alumnos) {
		log.info("Inicio metodo Crear Alumnos");
		AlumnoResponseRest response = new AlumnoResponseRest();
		List<Alumno> list = new ArrayList<>();
		try {
			Alumno alumnoGuardado = AlumnoDao.save(alumnos);
			if (alumnoGuardado != null) {
				list.add(alumnoGuardado);
				response.getAlumnoResponse().setAlumnos(list);
			} else {
				log.error("Error en Grabar Alumno");
				response.setMetadata("Respuesta no esta OK", "-1", "Alumno no guardado");
				return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Error en Grabar Alumno");
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Alumno Creado");
		return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<AlumnoResponseRest> modificar(Alumno alumno, Long id) {
		log.info("Inicio metodo Actualizar Alumnos");
		AlumnoResponseRest response = new AlumnoResponseRest();
		List<Alumno> list = new ArrayList<>();
		try {
			Optional<Alumno> alumnoGuardado = AlumnoDao.findById(id);
			if (alumnoGuardado.isPresent()) {
				alumnoGuardado.get().setDni(alumno.getDni());
				alumnoGuardado.get().setNombreAlumno(alumno.getNombreAlumno());
				alumnoGuardado.get().setDireccion(alumno.getDireccion());
				Alumno alumnoActualizado = AlumnoDao.save(alumnoGuardado.get());
				if (alumnoActualizado != null) {
					response.setMetadata("Respuesta OK", "00", "Alumno Actualizada");
					list.add(alumnoActualizado);
					response.getAlumnoResponse().setAlumnos(list);
				} else {
					log.error("Error en Grabar Alumno");
					response.setMetadata("Respuesta no esta OK", "-1", "Alumno no guardado");
					return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception e) {
			log.error("Error en Grabar Alumno");
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AlumnoResponseRest> eliminar(Long id) {
		log.info("Inicio metodo Actualizar Alumnos");
		AlumnoResponseRest response = new AlumnoResponseRest();
		try {
			Optional<Alumno> alumnoGuardado = AlumnoDao.findById(id);
			if (alumnoGuardado.isPresent()) {
				AlumnoDao.delete(alumnoGuardado.get());
				response.setMetadata("Respuesta OK", "00", "Alumno Eliminado");
			} else {
				response.setMetadata("Respuesta no esta OK", "-1", "Alumno no eliminado");
				return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<AlumnoResponseRest>(response, HttpStatus.OK);
	}
}
