package br.com.phoenix.toolschallenge.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.phoenix.toolschallenge.model.Transacao;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PagamentoDTO {
	
	//Entidade Criada com Intuito de seguir o exemplo enviado

	private Transacao transacao;

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}

}
