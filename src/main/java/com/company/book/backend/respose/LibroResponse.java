package com.company.book.backend.respose;
import java.util.List;
import com.company.book.backend.model.Libro;


public class LibroResponse {
	private List<Libro> libro;	
	public List<Libro> getLibro() {
		return libro;
	}
	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
}
