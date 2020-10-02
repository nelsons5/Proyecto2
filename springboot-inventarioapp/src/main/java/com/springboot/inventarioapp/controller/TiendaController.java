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

import com.springboot.inventarioapp.models.entity.Tienda;
import com.springboot.inventarioapp.models.service.ITiendaService;

@Controller
@RequestMapping("/views/tiendas")
public class TiendaController {
	@Autowired
	private ITiendaService tiendaService;
	@GetMapping("/")
	public String listarTiendas(Model model) {
		List<Tienda> listadoTiendas= tiendaService.listarTodos();
		model.addAttribute("titulo", "Listado de Tiendas");
		model.addAttribute("tiendas",listadoTiendas);
		return "/views/tiendas/listar";
	}
	
	
	@GetMapping("/create")
	public String crear(Model model) {

		Tienda tienda = new Tienda();
		model.addAttribute("titulo", "Crear Tienda");
		model.addAttribute("tienda", tienda);
		
		return "/views/tiendas/frmCrear";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Tienda tienda, BindingResult result,
			Model model, RedirectAttributes attribute) {
	

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Ingreso nueva tienda");
			model.addAttribute("tienda", tienda);
			System.out.println("Informacion no valida");			
			return "/views/tiendas/frmCrear";
		}
		
		tiendaService.guardar(tienda);
		System.out.println("Tienda guardada con exito!");
		attribute.addFlashAttribute("success", "Tienda guardada con exito!");
		return "redirect:/views/tiendas/";
	}	
		
		
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idTienda, Model model, RedirectAttributes attribute) {
			
		Tienda tienda = null;
		
		if (idTienda > 0) {
			tienda = tiendaService.buscarPorId(idTienda);
			
			if (tienda == null) {
				System.out.println("Error: Tienda no Existe");
				attribute.addFlashAttribute("error", "ATENCION: El ID de la tienda no existe!");
				return "redirect:/views/tiendas/";
			}
		}else {
			System.out.println("Error: Error con el ID de la tienda");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID de la tienda");
			return "redirect:/views/tiendas/";
		}
		

		model.addAttribute("titulo", "Formulario: Editar Tienda");
		model.addAttribute("tienda", tienda);
		

		return "/views/tiendas/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idTienda, RedirectAttributes attribute) {

		Tienda tienda = null;
		
		if (idTienda > 0) {
			tienda = tiendaService.buscarPorId(idTienda);
			
			if (tienda == null) {
				System.out.println("Error: El ID de la tienda no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID de la tienda no existe!");
				return "redirect:/views/tiendas/";
			}
		}else {
			System.out.println("Error: Error con el ID de la tienda");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID de la tienda!");
			return "redirect:/views/tiendas/";
		}		
		
		tiendaService.eliminar(idTienda);
		System.out.println("Registro Eliminado con Exito!");
		attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

		return "redirect:/views/tiendas/";
	}	

}
