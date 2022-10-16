package br.com.phoenix.toolschallenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.phoenix.toolschallenge.model.enums.TipoFormaPagamento;

@Entity
@Table(name = "FormaPagamento")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FormaPagamento {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long id;

	@NotNull
	private TipoFormaPagamento tipo;

	@Positive
	private Long parcelas;

	public FormaPagamento() {
	}

	public FormaPagamento(TipoFormaPagamento tipo, Long parcelas) {
		this.tipo = tipo;
		this.parcelas = parcelas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoFormaPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoFormaPagamento tipo) {
		this.tipo = tipo;
	}
	
	//Construtor Criado para que seguir exemplo apresentado
	public void setTipo(String tipo) {
		this.tipo = TipoFormaPagamento.getNome(tipo);
	}

	public Long getParcelas() {
		return parcelas;
	}

	public void setParcelas(Long parcelas) {
		this.parcelas = parcelas;
	}

}
