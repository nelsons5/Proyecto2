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
import com.springboot.inventarioapp.models.entity.Tienda;

import com.springboot.inventarioapp.models.entity.Ingreso;
import com.springboot.inventarioapp.models.service.IIngresoService;
import com.springboot.inventarioapp.models.service.IProductoService;
import com.springboot.inventarioapp.models.service.ITiendaService;

@Controller
@RequestMapping("/views/ingresos")
public class IngresoController {

	@Autowired
	private IIngresoService ingresoService;

	@Autowired
	private IProductoService productoService;

	@Autowired
	private ITiendaService tiendaService;
	
	
	
	@GetMapping("/")
	public String listarIngresos(Model model) {
		List<Ingreso> listadoIngresos = ingresoService.listarTodos();

		model.addAttribute("titulo", "Lista de Ingresos");
		model.addAttribute("ingresos", listadoIngresos);

		return "/views/ingresos/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {

		Ingreso ingreso = new Ingreso();
		List<Producto> listProductos = productoService.listarTodos();
		List<Tienda> listTiendas = tiendaService.listarTodos();

		model.addAttribute("titulo", "Formulario: Nuevo Ingreso");
		model.addAttribute("ingreso", ingreso);
		model.addAttribute("productos", listProductos);
		model.addAttribute("tiendas", listTiendas);
		return "/views/ingresos/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Ingreso ingreso, BindingResult result,
			Model model, RedirectAttributes attribute) {
		List<Producto> listProductos = productoService.listarTodos();
		List<Tienda> listTiendas = tiendaService.listarTodos();

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Ingreso");
			model.addAttribute("ingreso", ingreso);
			model.addAttribute("productos", listProductos);
			model.addAttribute("tiendas", listTiendas);
			
			System.out.println("Existieron errores en el formulario");			
			return "/views/ingresos/frmCrear";
		}

		ingresoService.guardar(ingreso);
		System.out.println("Ingreso registrado con exito!");
		attribute.addFlashAttribute("success", "Ingreso registrado con exito!");
		return "redirect:/views/ingresos/";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idIngreso, Model model, RedirectAttributes attribute) {
			
		Ingreso ingreso = null;
		
		if (idIngreso > 0) {
			ingreso = ingresoService.buscarPorId(idIngreso);
			
			if (ingreso == null) {
				System.out.println("Error: El ID del ingreso no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del ingreso no existe!");
				return "redirect:/views/ingresos/";
			}
		}else {
			System.out.println("Error: Error con el ID del Ingreso");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del ingreso");
			return "redirect:/views/ingresos/";
		}
		
		List<Producto> listProductos = productoService.listarTodos();
		List<Tienda> listTiendas = tiendaService.listarTodos();
		

		model.addAttribute("titulo", "Formulario: Editar Ingreso");
		model.addAttribute("ingreso", ingreso);
		model.addAttribute("productos", listProductos);
		model.addAttribute("tiendas", listTiendas);
		

		return "/views/ingresos/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idIngreso, RedirectAttributes attribute) {

		Ingreso ingreso = null;
		
		if (idIngreso > 0) {
			ingreso = ingresoService.buscarPorId(idIngreso);
			
			if (ingreso == null) {
				System.out.println("Error: El ID del ingreso no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del ingreso no existe!");
				return "redirect:/views/ingresos/";
			}
		}else {
			System.out.println("Error: Error con el ID del ingreso");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del Ingreso!");
			return "redirect:/views/ingresos/";
		}		
		
		ingresoService.eliminar(idIngreso);
		System.out.println("Registro Eliminado con Exito!");
		attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

		return "redirect:/views/ingresos/";
	}

}
