package br.com.phoenix.toolschallenge.model.enums;

public enum TipoFormaPagamento {

	AVISTA("AVISTA", "Pagamento a vista"), PARCELADO_LOJA("PARCELADO LOJA", "Parcelamento realizado pela Loja"),
	PARCELADO_EMISSOR("PARCELADO EMISSOR", "Parcelamento realizado pelo emissor");

	private String descricao;
	
	//Parametro utilizado para validar a chamada
	private String value;

	TipoFormaPagamento(String value, String descricao) {

		this.value = value;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
