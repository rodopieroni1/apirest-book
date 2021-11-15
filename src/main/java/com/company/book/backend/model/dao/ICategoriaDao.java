package com.company.book.backend.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.company.book.backend.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long>{

}
