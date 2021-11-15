package com.company.book.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.book.backend.model.Libro;

public interface ILibroDao extends CrudRepository<Libro,Long> {

}
