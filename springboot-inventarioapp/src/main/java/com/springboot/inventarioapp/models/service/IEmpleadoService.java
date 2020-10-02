package com.springboot.inventarioapp.models.service;

import java.util.List;
import com.springboot.inventarioapp.models.entity.Empleado;
public interface IEmpleadoService {
	
	public  List<Empleado> listarTodos();
	public void guardar (Empleado empleado);
	public Empleado buscarPorId(Long id);
	public void eliminar (Long id);

}
