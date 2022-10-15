package br.com.phoenix.toolschallenge.model;

public enum SituacaoTarefa {

	EM_ABERTO("Em Aberto"), EM_ANDAMENTO("Em Andamento"), CONCLUIDA("Concluida"), CANCELADA("Cancelada");

	private String descricao;

	private SituacaoTarefa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
