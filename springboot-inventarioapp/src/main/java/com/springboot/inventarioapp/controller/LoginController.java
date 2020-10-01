package com.springboot.inventarioapp.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error, Model model
			,Principal principal, RedirectAttributes attribute) {
		
		if (error!=null) {
			model.addAttribute(error,"Usuario/Password Incorrectos");
		}
		
		
		if(principal!=null) {
			
			attribute.addFlashAttribute("warning","Usuario ya cuenta con una sesion activa");
			return "redirect:/home"; 
				
		}
			
		
		return "login";
	}

}
