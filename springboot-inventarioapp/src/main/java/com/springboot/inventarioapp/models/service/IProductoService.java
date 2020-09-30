package com.springboot.inventarioapp.models.service;

import java.util.List;
import com.springboot.inventarioapp.models.entity.Producto;
public interface IProductoService {
	
	public  List<Producto> listarTodos();
	public void guardar (Producto producto);
	public Producto buscarPorId(Long id);
	public void eliminar (Long id);

}
