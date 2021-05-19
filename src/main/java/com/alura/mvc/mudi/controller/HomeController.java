package com.alura.mvc.mudi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alura.mvc.mudi.model.StatusPedido;
import com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home") // Toda requisição /home vai cair nesse Controller
public class HomeController {

	@Autowired
	private PedidoRepository repo;
	
	@GetMapping // Toda requisição /home sem um sufixo, cairá nesse método (default)
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView("home"); // nome da página
	    mv.addObject("pedidos", repo.findAll()); // valores que serão "jogados" para renderização na página
		return mv;
	}
	
	@GetMapping("/{status}") // Esperamos receber um status como parâmeto
	public ModelAndView porStatus(@PathVariable(name = "status") String status) { // Recebendo o status na variável
		ModelAndView mv = new ModelAndView("home");
	    mv.addObject("pedidos", repo.findByStatus(StatusPedido.valueOf(status.toUpperCase())));
	    mv.addObject("status", status);
		return mv;
	}
	
	// Caso a pessoa tente acessar um status diferente, cai nessa exceção que redirecionará o usuário para a /home
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
