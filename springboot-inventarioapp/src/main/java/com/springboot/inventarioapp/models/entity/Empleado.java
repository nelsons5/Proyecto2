package com.springboot.inventarioapp.models.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empleados")
public class Empleado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private Long id_tienda;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Long getId_tienda() {
		return id_tienda;
	}
	public void setId_tienda(Long id_tienda) {
		this.id_tienda = id_tienda;
	}
	
	
	@Override
	public String toString() {
		return "Empleados [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", id_tienda=" + id_tienda
				+ "]";
	}
	
	
}
