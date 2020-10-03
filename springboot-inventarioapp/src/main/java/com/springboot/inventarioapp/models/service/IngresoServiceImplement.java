package com.springboot.inventarioapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.inventarioapp.models.entity.Ingreso;
import com.springboot.inventarioapp.models.repository.IngresoRepository;

@Service
public class IngresoServiceImplement implements IIngresoService {
	
	@Autowired
	private IngresoRepository ingresoRepository;
	
	@Override
	public List<Ingreso> listarTodos() {
		return (List<Ingreso>) ingresoRepository.findAll();
	}

	@Override
	public void guardar(Ingreso ingreso) {
		ingresoRepository.save(ingreso);
	}

	@Override
	public Ingreso buscarPorId(Long id)  {		
		return ingresoRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id){		
		ingresoRepository.deleteById(id);
	}

}
