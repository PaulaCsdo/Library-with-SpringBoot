package com.ite.paulacasadogarcia.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite.paulacasadogarcia.modelo.bean.Libro;
import com.ite.paulacasadogarcia.modelo.bean.Perfile;
import com.ite.paulacasadogarcia.modelo.bean.Tema;
import com.ite.paulacasadogarcia.modelo.bean.Usuario;
import com.ite.paulacasadogarcia.modelo.dao.LibroInt;
import com.ite.paulacasadogarcia.modelo.dao.PerfilesInt;
import com.ite.paulacasadogarcia.modelo.dao.TemaInt;
import com.ite.paulacasadogarcia.modelo.dao.UsuarioInt;

@Controller
@RequestMapping("/admon")
public class AdmonController {
	
	@Autowired
	private UsuarioInt uint;
	
	@Autowired
	private LibroInt lint;
	
	@Autowired
	private TemaInt tint;
	
	@Autowired
	private PerfilesInt perint;
	
	
	/*
	 * Cuando el usuario está registrado, puede volver al menú principal desde 
	 * cualquier página
	 */
	@GetMapping("/inicio")
	public String listarCosas(Model model) {
		List<Libro> nov=lint.listarNovedad();
		List<Usuario> usu=uint.findAll();
		List<Perfile> per=perint.findAll();
		List<Tema> tem=tint.findAll();
		model.addAttribute("listaNovedades", nov);
		model.addAttribute("listaUsuarios", usu);
		model.addAttribute("listaPerfiles", per);
		model.addAttribute("listaTemas", tem);
		return "MenuInicio";
	}
	
	@GetMapping("/verDetalle/{isbn}")
	public String verDetalleLibro(Model model, @PathVariable("isbn") long isbn) {
		model.addAttribute("libro", lint.findById(isbn));
		return "Detalles";
	}
	
	/*
	 * Buscador de temas: buscar los libros de una categoría temática
	 */
	@GetMapping("/buscarTemas")
	private String buscadorDeTemas() {
		return "BuscarTema";
	}
	
	@PostMapping("/buscarTemas")
	private String formTemas(@RequestParam("abreviatura") String abreviatura, RedirectAttributes attr, HttpSession session) {
		System.out.println(abreviatura);
		List<Libro> libroTema= lint.buscarPorTema(abreviatura);
		attr.addFlashAttribute("listaTema", libroTema);
		return "redirect:/admon/buscarTemas";
	}
	
	/*
	 * Buscador de titulos: buscar un libro mediante una cadena de caracteres
	 */
	@GetMapping("/buscarTitulos")
	private String buscadorDeTitulos() {
		return "BuscarTitulo";
	}
	
	@PostMapping("/buscarTitulos")
	private String formTitulos(@RequestParam("titulo") String titulo, RedirectAttributes attr, HttpSession session) {
		List<Libro> libroTitulo= lint.buscarPorTitulo(titulo);
		attr.addFlashAttribute("listaTitulo", libroTitulo);
		return "redirect:/admon/buscarTitulos";
	}
	
	//Eliminar un libro
	@GetMapping("/eliminar/{isbn}")
	public String eliminarLibro(@PathVariable("isbn") long isbn, Model model, HttpSession session) {
		
		if (lint.eliminar(isbn) == 0) {
			List<Libro> listaNov = lint.listarNovedad();
			model.addAttribute("listaNovedades", listaNov);
			model.addAttribute("mensaje", "Error al eliminar el articulo");
			return "MenuInicio";
		}else {
			List<Libro> listaNov = lint.listarNovedad();
			model.addAttribute("listaNovedades", listaNov);
			return "MenuInicio";
		}

	}
	
	//Editar un libro
	@GetMapping("/editar/{isbn}")
	public String editarLibro(@PathVariable("isbn") long isbn) {
		
		return "EditarLibro";
	}
	


}
