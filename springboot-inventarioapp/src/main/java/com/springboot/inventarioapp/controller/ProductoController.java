package com.springboot.inventarioapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Producto producto, BindingResult result,
			Model model, RedirectAttributes attribute) {
	

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Ingreso nuevo producto");
			model.addAttribute("producto", producto);
			System.out.println("Informacion no valida");			
			return "/views/productos/frmCrear";
		}
		
		productoService.guardar(producto);
		System.out.println("Cliente guardado con exito!");
		attribute.addFlashAttribute("success", "Cliente guardado con exito!");
		return "redirect:/views/productos/";
	}	
		
		
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idProducto, Model model, RedirectAttributes attribute) {
			
		Producto producto = null;
		
		if (idProducto > 0) {
			producto = productoService.buscarPorId(idProducto);
			
			if (producto == null) {
				System.out.println("Error: El Producto no Existe");
				attribute.addFlashAttribute("error", "ATENCION: El ID del producto no existe!");
				return "redirect:/views/productos/";
			}
		}else {
			System.out.println("Error: Error con el ID del Producto");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del producto");
			return "redirect:/views/clientes/";
		}
		

		model.addAttribute("titulo", "Formulario: Editar Producto");
		model.addAttribute("producto", producto);
		

		return "/views/productos/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idProducto, RedirectAttributes attribute) {

		Producto producto = null;
		
		if (idProducto > 0) {
			producto = productoService.buscarPorId(idProducto);
			
			if (producto == null) {
				System.out.println("Error: El ID del producto no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del producto no existe!");
				return "redirect:/views/productos/";
			}
		}else {
			System.out.println("Error: Error con el ID del producto");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del Producto!");
			return "redirect:/views/clientes/";
		}		
		
		productoService.eliminar(idProducto);
		System.out.println("Registro Eliminado con Exito!");
		attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

		return "redirect:/views/productos/";
	}	

}
