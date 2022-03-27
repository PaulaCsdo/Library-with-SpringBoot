package com.ite.paulacasadogarcia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.paulacasadogarcia.modelo.dao.LibroInt;
import com.ite.paulacasadogarcia.modelo.dao.PerfilesInt;
import com.ite.paulacasadogarcia.modelo.dao.TemaInt;
import com.ite.paulacasadogarcia.modelo.dao.UsuarioInt;


import com.ite.paulacasadogarcia.modelo.bean.Libro;
import com.ite.paulacasadogarcia.modelo.bean.Perfile;
import com.ite.paulacasadogarcia.modelo.bean.Tema;
import com.ite.paulacasadogarcia.modelo.bean.Usuario;

@Controller
public class HomeController {
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	// Cifrado
//	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//	 // Descifrar
//	 bCryptPasswordEncoder.matches (contraseña // contraseña pasada,
//	user.getPassword () // contraseña encontrada en la base de datos)
	
	@Autowired
	private UsuarioInt uint;
	
	@Autowired
	private LibroInt lint; 
	
	@Autowired
	private TemaInt tint;
	
	@Autowired
	private PerfilesInt perint;
	
	@Autowired
	private PasswordEncoder pwcript;
	
	@GetMapping("/sesionIniciada")
	public String inicio(HttpSession session,Authentication auth, Model model) {
		Usuario usuario=uint.findById(auth.getName());
		if (usuario==null) {
			return "redirect:/login";
		}
		session.setAttribute("usuario", usuario);
		
		List<Libro> lib = lint.listarNovedad();
		List<Usuario> usu=uint.findAll();
		List<Perfile> per=perint.findAll();
		List<Tema> tem=tint.findAll();
		model.addAttribute("listaUsuarios", usu);
		model.addAttribute("listaPerfiles", per);
		model.addAttribute("listaTemas", tem);
		model.addAttribute("listaNovedades", lib);
		
		return "MenuInicio";
		
	}
	
	@GetMapping("/encriptar/{id}") 
	@ResponseBody 
	public String encriptar(@PathVariable("id") String texto) { 
	String newPassw= null; 
	 
	newPassw = "el texto es: " + pwcript.encode(texto); 
	return newPassw; 
	}

	@GetMapping("/registro")
	public String formRegistro() {
		return "RegistroCliente";
	}

	@PostMapping("/registro")
	public String procRegistro(Usuario usuario, RedirectAttributes attr) {
		if (usuario == null) {
			attr.addFlashAttribute("mensaje", "Error en el alta");
			return "redirect:/registro";

		}else {
			usuario.setEnabled(1);
			usuario.setFechaAlta(new Date());

			Perfile per = perint.findById(2);
			List<Perfile> lista = new ArrayList<Perfile>();
			lista.add(per);
			usuario.setPerfiles(lista);
			uint.alta(usuario);
			attr.addFlashAttribute("mensaje", "Nuevo usuario creado");
			return "redirect:/registro";
		}

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request,null,null);
		return "redirect:/login";
	}

}
