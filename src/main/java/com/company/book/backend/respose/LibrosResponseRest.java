package com.company.book.backend.respose;
public class LibrosResponseRest extends ResponceRest {

	private LibroResponse libroResponse= new  LibroResponse();
	public LibroResponse getLibroResponse() {
		return libroResponse;
	}
	public void setLibroResponse(LibroResponse libroResponse) {
		this.libroResponse = libroResponse;
	}
}
