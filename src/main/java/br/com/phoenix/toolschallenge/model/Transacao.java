package br.com.phoenix.toolschallenge.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Transacao")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Transacao {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "cartao")
	private String cartao;

	@Valid
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false, targetEntity = DescricaoTransacao.class)
	@JoinColumn(name = "descricao_id", nullable = false)
	private DescricaoTransacao descricao;

	@Valid
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false, targetEntity = FormaPagamento.class)
	@JoinColumn(name = "formaPagamento_id", nullable = false)
	private FormaPagamento formaPagamento;

	public Transacao(Long id, String cartao, DescricaoTransacao descricao, FormaPagamento formaPagamento) {
		this.id = id;
		this.cartao = cartao;
		this.descricao = descricao;
		this.formaPagamento = formaPagamento;
	}

	public Transacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public DescricaoTransacao getDescricao() {
		return descricao;
	}

	public void setDescricao(DescricaoTransacao descricao) {
		this.descricao = descricao;
	}
}
