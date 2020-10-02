package com.springboot.inventarioapp.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.inventarioapp.models.entity.Tienda;
import com.springboot.inventarioapp.models.repository.TiendaRepository;

@Service
public class TiendaServiceImplement implements ITiendaService {
	
	@Autowired
	private TiendaRepository tiendaRepository;
	@Override
	public List<Tienda> listarTodos() {
		
		
		return (List<Tienda>) tiendaRepository.findAll();
	}

	@Override
	public void guardar(Tienda tienda) {
		
		tiendaRepository.save(tienda);
		
}

	@Override
	public Tienda buscarPorId(Long id) {
		
		return tiendaRepository.findById(id).orElse(null);
		
	}

	@Override
	public void eliminar(Long id) {
		tiendaRepository.deleteById(id);

	}

}
