package br.com.phoenix.toolschallenge.model.enums;

public enum StatusTransacao {

	AUTORIZADO("Transação autorizada"), NEGADO("Transação Negada");

	private String descricao;

	StatusTransacao(String descricao) {

		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
