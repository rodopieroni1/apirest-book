package com.company.book.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alumnos")
public class Alumno implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlumno;
	private String nombreAlumno;
	private int dni;
	private String direccion;

	
	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Alumno(Long idAlumno, String nombreAlumno, int dni, String direccion) {
		super();
		this.idAlumno = idAlumno;
		this.nombreAlumno = nombreAlumno;
		this.dni = dni;
		this.direccion = direccion;
	}

	public Long getIdAlumno() {
		return idAlumno;
	}
	
	public void setIdAlumno(Long idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
