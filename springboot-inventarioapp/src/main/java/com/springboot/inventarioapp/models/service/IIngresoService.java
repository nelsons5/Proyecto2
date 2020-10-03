package com.springboot.inventarioapp.models.service;

import java.util.List;

import com.springboot.inventarioapp.models.entity.Ingreso;

public interface IIngresoService {
	
	public List<Ingreso> listarTodos();
	public void guardar(Ingreso ingreso);
	public Ingreso buscarPorId(Long id);
	public void eliminar(Long id);
	
}
