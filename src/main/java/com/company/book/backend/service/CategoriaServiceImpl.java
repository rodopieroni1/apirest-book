package com.company.book.backend.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.book.backend.model.Categoria;
import com.company.book.backend.model.dao.ICategoriaDao;
import com.company.book.backend.respose.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

	@Autowired
	private ICategoriaDao categoriaDao;

	@Override
	@Transactional // (readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		log.info("Inicio del metodo buscarCategoria()");
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			log.error("Error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// devuelve
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// devuelve 200
	}

	@Override
	@Transactional // (readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
		log.info("Inicio metodo buscarPorId");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		try {
			Optional<Categoria> categoria = categoriaDao.findById(id);
			if (categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("Error en consultar categoria");
				response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en consultar categoria");
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// devuelve 200
	}

	@Override
	@Transactional//(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
		log.info("Inicio metodo CrearCategoria");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		try {
			Categoria categoriaGuardada = categoriaDao.save(categoria);
			if (categoriaGuardada != null) {
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("Error en Grabar categoria");
				response.setMetadata("Respuesta no esta OK", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error("Error en grabar categoria");
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.setMetadata("Respuesta OK", "00", "Categoria Creada");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> modificar(Categoria categoria, Long id) {
		log.info("Inicio metodo CrearCategoria");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		try {
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get());
				if (categoriaActualizar != null) {
					response.setMetadata("Respuesta OK", "00", "Categoria Actualizada");
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
				} else {
					log.error("Error  en actualizar la categoria");
					response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);

				}
			}
		} catch (Exception e) {
			log.error("Error  en actualizar la categoria", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
		log.info("Inicio metodo Eliminar");
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {
			categoriaDao.deleteById(id);
			response.setMetadata("Respuesta OK", "00", "Categoria Eliminada");
			} catch (Exception e) {
				log.error("Error en la elimnacion de la categoria");
				response.setMetadata("Respuesta no esta OK", "-1", "Respuesta NO Exitosa");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
}
