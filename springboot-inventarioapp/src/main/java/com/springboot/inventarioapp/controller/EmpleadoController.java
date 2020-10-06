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

import com.springboot.inventarioapp.models.entity.Empleado;
import com.springboot.inventarioapp.models.entity.Tienda;
import com.springboot.inventarioapp.models.service.IEmpleadoService;
import com.springboot.inventarioapp.models.service.ITiendaService;

@Controller
@RequestMapping("/views/empleados")
public class EmpleadoController {
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private ITiendaService tiendaService;
	
	@GetMapping("/")
	public String listarEmpleados(Model model) {
		List<Empleado> listadoEmpleados= empleadoService.listarTodos();
		model.addAttribute("titulo", "Listado de Empleados");
		model.addAttribute("empleados",listadoEmpleados);
		return "/views/empleados/listar";
	}
	
	
	@GetMapping("/create")
	public String crear(Model model) {

		Empleado empleado = new Empleado();
		List<Tienda> listTiendas = tiendaService.listarTodos();
		
		model.addAttribute("titulo", "Agregar empleado");
		model.addAttribute("empleado", empleado);
		model.addAttribute("tiendas", listTiendas);
		
		return "/views/empleados/frmCrear";
	}
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Empleado empleado, BindingResult result,
			Model model, RedirectAttributes attribute) {
		List<Tienda> listTiendas = tiendaService.listarTodos();


		if (result.hasErrors()) {
			model.addAttribute("titulo", "Ingreso nuevo empleado");
			model.addAttribute("empleado", empleado);
			model.addAttribute("tiendas", listTiendas);
			System.out.println("Informacion no valida");			
			return "/views/empleados/frmCrear";
		}
		
		empleadoService.guardar(empleado);
		System.out.println("Empleado guardado con exito!");
		attribute.addFlashAttribute("success", "Empleado guardado con exito!");
		return "redirect:/views/empleados/";
	}	
		
		
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idEmpleado, Model model, RedirectAttributes attribute) {
			
		Empleado empleado = null;
		
		if (idEmpleado > 0) {
			empleado = empleadoService.buscarPorId(idEmpleado);
			
			if (empleado == null) {
				System.out.println("Error: El Empleado no Existe");
				attribute.addFlashAttribute("error", "ATENCION: El ID del empleado no existe!");
				return "redirect:/views/empleados/";
			}
		}else {
			System.out.println("Error: Error con el ID del Empleado");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del empleado");
			return "redirect:/views/empleados/";
		}
		List<Tienda> listTiendas = tiendaService.listarTodos();

		model.addAttribute("titulo", "Formulario: Editar Empleado");
		model.addAttribute("empleado", empleado);
		model.addAttribute("tiendas", listTiendas);

		return "/views/empleados/frmCrear";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idEmpleado, RedirectAttributes attribute) {

		Empleado empleado = null;
		
		if (idEmpleado > 0) {
			empleado = empleadoService.buscarPorId(idEmpleado);
			
			if (empleado == null) {
				System.out.println("Error: El ID del empleado no existe!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del empleado no existe!");
				return "redirect:/views/empleados/";
			}
		}else {
			System.out.println("Error: Error con el ID del empleado");
			attribute.addFlashAttribute("error", "ATENCION: Error con el ID del Empleado!");
			return "redirect:/views/empleados/";
		}		
		
		empleadoService.eliminar(idEmpleado);
		System.out.println("Registro Eliminado con Exito!");
		attribute.addFlashAttribute("warning", "Registro Eliminado con Exito!");

		return "redirect:/views/empleados/";
	}	

}
