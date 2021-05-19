package com.alura.mvc.mudi.controller.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.alura.mvc.mudi.model.Pedido;
import com.alura.mvc.mudi.model.StatusPedido;

/**
 * POJO resnposável por armazenar as informações recebidas.
 * 
 * Todos os atributos desse DTO são os "names" dos inputs do formulário que está
 * na página formulario.html
 * 
 * As mensagens de erro customizadas estão em messages.properties. Nenhuma
 * configuração adicional foi necessária. Bastou criar o arquivo e criar as
 * mensagens
 * 
 * Sobre as anotações do Bean Validation:
 * 
 * https://docs.jboss.org/hibernate/beanvalidation/spec/2.0/api/javax/validation/constraints/package-summary.html
 *
 */
public class RequisicaoNovoPedidoDto {

	@NotBlank // Não pode ser nulo e nem vazio
	@Length(min = 5, max = 50)
	private String nomeProduto;

	@NotBlank
	@Length(min = 5, max = 100)
	private String urlProduto;

	@NotBlank
	@Length(min = 5, max = 100)
	private String urlImagem;

	private String descricao;

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setNomeProduto(nomeProduto);
		pedido.setUrlProduto(urlProduto);
		pedido.setUrlImagem(urlImagem);
		pedido.setDescricao(descricao);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		return pedido;
	}

}
