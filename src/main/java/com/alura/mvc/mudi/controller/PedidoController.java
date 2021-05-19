package com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alura.mvc.mudi.controller.dto.RequisicaoNovoPedidoDto;
import com.alura.mvc.mudi.model.Pedido;
import com.alura.mvc.mudi.repository.PedidoRepository;

/**
 * Informação sobre diferença entre RequestMapping, PostMapping e etc:
 * https://pt.stackoverflow.com/questions/365952/diferen%C3%A7a-de-met%C3%B3dos-com-getmapping-e-requestmapping
 * 
 * https://qastack.com.br/programming/39077787/difference-between-the-annotations-getmapping-and-requestmappingmethod-requ#:~:text=Resposta%20curta%3A,GET).
 * 
 * @author thiag
 *
 */
@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepo;

	/*
	 * Precisamos receber uma instância de RequisicaoNovoPedidoDto, senão, o HTML
	 * não consegue renderizar por precisar de uma instância do mesmo.
	 */
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedidoDto requisicao) {
		return "pedido/formulario";
	}

	/*
	 * @Valid somente vai funcionar pois anotamos os atributos de
	 * RequisicaoNovoPedidoDto com as validações
	 */
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedidoDto requisicao, BindingResult result) {
		/*
		 * Como estamos trabalhando com Thymeleaf, quando alguma informação estiver
		 * incorreta e redirecionarmos o usuário para a tela de formulário, o mesmo
		 * objeto de RequisicaoNovoPedidoDto será devolvido para o HTML.
		 */
		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		Pedido pedido = requisicao.toPedido();
		pedidoRepo.save(pedido);
		return "redirect:/home";
	}

}
