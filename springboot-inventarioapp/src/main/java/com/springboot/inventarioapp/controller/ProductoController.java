package com.springboot.inventarioapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.inventarioapp.models.entity.Producto;
import com.springboot.inventarioapp.models.service.IProductoService;

@Controller
@RequestMapping("/views/productos")
public class ProductoController {
	@Autowired
	private IProductoService productoService;
	@GetMapping("/")
	public String listarProductos(Model model) {
		List<Producto> listadoProductos= productoService.listarTodos();
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos",listadoProductos);
		return "/views/productos/listar";
	}
	
	
	@GetMapping("/create")
	public String crear(Model model) {

		Producto producto = new Producto();
		model.addAttribute("titulo", "Agregar producto");
		model.addAttribute("producto", producto);
		
		return "/views/productos/frmCrear";
	}
		
		
	
	

}
