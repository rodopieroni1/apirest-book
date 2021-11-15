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

import com.company.book.backend.model.Libro;
import com.company.book.backend.model.dao.ILibroDao;
import com.company.book.backend.respose.LibrosResponseRest;

@Service
public class LibrosServiceImpl implements ILibroService {

	private static final Logger log = LoggerFactory.getLogger(LibrosServiceImpl.class);

	@Autowired
	private ILibroDao LibroDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibrosResponseRest> buscarLibros() {
		log.info("Inicio del metodo buscarLibros()");
		LibrosResponseRest response = new LibrosResponseRest();
		try {
			List<Libro> libro = (List<Libro>) LibroDao.findAll();
			response.getLibroResponse().setLibro(libro);
			response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			log.error("Error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// devuelve
		}
		return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.OK);// devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<LibrosResponseRest> buscarPorId(Long id) {
		log.info("Inicio metodo buscarPorId");
		LibrosResponseRest response = new LibrosResponseRest();
		List<Libro> list = new ArrayList<>();
		try {
			Optional<Libro> libro = LibroDao.findById(id);
			if (libro.isPresent()) {
				list.add(libro.get());
				response.getLibroResponse().setLibro(list);
			} else {
				log.error("Error en consultar libro");
				response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
				return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en consultar libro");
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.OK);// devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<LibrosResponseRest> crear(Libro libros) {
		log.info("Inicio metodo CrearLibros");
		LibrosResponseRest response = new LibrosResponseRest();
		List<Libro> list = new ArrayList<>();
		try {
			Libro libroGuardado = LibroDao.save(libros);
			if (libroGuardado != null) {
				list.add(libroGuardado);
				response.getLibroResponse().setLibro(list);
			} else {
				log.error("Error en Grabar Libro");
				response.setMetadata("Respuesta no esta OK", "-1", "Libro no guardada");
				return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Error en grabar categoria");
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Libro Creada");
		return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<LibrosResponseRest> modificar(Libro libros, Long id) {
		log.info("Inicio metodo CrearLibros");
		LibrosResponseRest response = new LibrosResponseRest();
		List<Libro> list = new ArrayList<>();
		try {

			Optional<Libro> libroBuscada =  LibroDao.findById(id);
			if (libroBuscada.isPresent()) {
				libroBuscada.get().setNombre(libros.getNombre());
				libroBuscada.get().setDescripcion(libros.getDescripcion());
				libroBuscada.get().setCategoria(libros.getCategoria());
				Libro libroActualizar = LibroDao.save(libroBuscada.get());
				if (libroActualizar != null) {
					response.setMetadata("Respuesta OK", "00", "Categoria Actualizada");
					list.add(libroActualizar);
					response.getLibroResponse().setLibro(list);
				} else {
					log.error("Error  en actualizar Libro");
					response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
					return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.NOT_FOUND);

				}
			}
		} catch (Exception e) {
			log.error("Error  en actualizar la Libro", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<LibrosResponseRest> eliminar(Long id) {
		log.info("Inicio metodo Eliminar");
		LibrosResponseRest response = new LibrosResponseRest();
		try {
			 LibroDao.deleteById(id);
			response.setMetadata("Respuesta OK", "00", "Categoria Eliminada");
			} catch (Exception e) {
				log.error("Error en la elimnacion de la categoria");
				response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
				return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibrosResponseRest>(response, HttpStatus.OK);
	}
}
