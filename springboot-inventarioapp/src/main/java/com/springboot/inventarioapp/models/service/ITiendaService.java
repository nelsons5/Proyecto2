package com.springboot.inventarioapp.models.service;

import java.util.List;
import com.springboot.inventarioapp.models.entity.Tienda;
public interface ITiendaService {
	
	public  List<Tienda> listarTodos();
	public void guardar (Tienda tienda);
	public Tienda buscarPorId(Long id);
	public void eliminar (Long id);

}
