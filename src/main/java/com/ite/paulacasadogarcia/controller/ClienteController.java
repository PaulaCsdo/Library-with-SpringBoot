package com.ite.paulacasadogarcia.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.ite.paulacasadogarcia.modelo.bean.Usuario;
import com.ite.paulacasadogarcia.modelo.bean.LineasPedido;
import com.ite.paulacasadogarcia.modelo.bean.Pedido;
import com.ite.paulacasadogarcia.modelo.bean.Libro;
import com.ite.paulacasadogarcia.modelo.dao.LibroInt;
import com.ite.paulacasadogarcia.modelo.dao.PedidoInt;
import com.ite.paulacasadogarcia.modelo.dao.UsuarioInt;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private UsuarioInt uint;
	
	@Autowired
	private PedidoInt pint;
	
	@Autowired
	private LibroInt lint;
	
	/*
	 * Cuando el usuario está registrado, puede volver al menú principal desde 
	 * cualquier página
	 */
	@GetMapping("/inicio")
	public String listarNov(Model model) {
		List<Libro> nov=lint.listarNovedad();
		model.addAttribute("listaNovedades", nov);
		return "MenuInicio";
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
		return "redirect:/cliente/buscarTemas";
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
		return "redirect:/cliente/buscarTitulos";
	}
	
	@GetMapping("/misDatos")
	public String verDatos() {
		return "MisDatos";
	}
	
	@GetMapping("/verDetalle/{isbn}")
	public String verDetalleLibro(Model model, @PathVariable("isbn") long isbn) {
		model.addAttribute("libro", lint.findById(isbn));
		return "Detalles";
	}
	
	
	@GetMapping("/añadirCarrito/{isbn}")
	public String añadirLibro(Model model, @PathVariable("isbn") long isbn, HttpSession session) {
		
		List<Libro> carrito = (List<Libro>)session.getAttribute("listarCarrito");
		Libro libro = lint.findById(isbn);
		
		//Si el carrito está vacío, creamos un nuevo ArrayList y lo guardamos en sesión
		if (carrito == null) {
			carrito = new ArrayList<Libro>();
			session.setAttribute("listarCarrito", carrito);
		}
		
		carrito.add(libro);
		session.setAttribute("listarCarrito", carrito);

		return "redirect:/cliente/inicio";
	}
	
	/*
	 * En este caso, actualizamos el carrito antes de mostrar la vista.
	 */
	@GetMapping("/verCarrito")
	public String abrirCarrito(HttpSession session, Usuario usuario, Model model) {
		List<Libro> carrito = (List<Libro>) session.getAttribute("listarCarrito");
		
		//Si el carrito está vacío, creamos un nuevo ArrayList y lo guardamos en sesión
		if (carrito == null) {
			carrito = new ArrayList<Libro>();
			session.setAttribute("listarCarrito", carrito);
		}

		model.addAttribute("listarCarrito", carrito);
		return "Carrito";
	}
	
	/*
	 * Al eliminar un artículo, actualizamos la lista de compra y volvemos
	 * a mostrar la vista de carrito
	 */
	@GetMapping("/eliminarArticulo/{isbn}")
	public String eliminarLibroCarrito(@PathVariable("isbn")long isbn,HttpSession session,Model model) {
		List<Libro> carrito=(List<Libro>)session.getAttribute("listarCarrito");
		Libro libro=lint.findById(isbn);
		
		carrito.remove(libro);
		session.setAttribute("listarCarrito", carrito);
		
		return "forward:/cliente/verCarrito";
	}
	
	/*
	 * Realizar compra:
	 * 1. Recuperamos el carrito de la sesion
	 * 2. Comprobamos que el carrito no está vacío, y damos de alta un nuevo pedido
	 */
	@GetMapping("/comprar")
	public String comprarLibro(HttpSession session, Model model) {

		List<Libro> carrito = (List<Libro>) session.getAttribute("listarCarrito");
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (carrito != null) {
			Pedido ped = new Pedido();

			ped.setUsuario(usuario);
			ped.setDireccionEntrega(usuario.getDireccion());
			ped.setEstado("Pedido tramitado");
			ped.setFechaAlta(new Date());

			List<LineasPedido> lineasp = new ArrayList<LineasPedido>();
			for (Libro ele : carrito) {
				LineasPedido lin = new LineasPedido();

				lin.setPedido(ped);
				lin.setCantidad(1);
				lin.setFechaAlta(new Date());
				lin.setLibro(ele);
				lin.setPrecioVenta(ele.getPrecioUnitario());
				lineasp.add(lin);
			}

			ped.setLineasPedidos(lineasp);
			pint.alta(ped);
			
			model.addAttribute("mensaje", "Compra realizada");
			return "Carrito";
		}

		else {
			model.addAttribute("mensaje", "Su carrito está vacío");
			return "Carrito";
		}
		

	}
	

}
