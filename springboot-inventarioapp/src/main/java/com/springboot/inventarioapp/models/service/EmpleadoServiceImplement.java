package com.springboot.inventarioapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.inventarioapp.models.entity.Empleado;
import com.springboot.inventarioapp.models.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImplement implements IEmpleadoService {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Override
	public List<Empleado> listarTodos() {
		
		
		return (List<Empleado>) empleadoRepository.findAll();
	}

	@Override
	public void guardar(Empleado empleado) {
		
		empleadoRepository.save(empleado);
		
}

	@Override
	public Empleado buscarPorId(Long id) {
		
		return empleadoRepository.findById(id).orElse(null);
		
	}

	@Override
	public void eliminar(Long id) {
		empleadoRepository.deleteById(id);

	}

}