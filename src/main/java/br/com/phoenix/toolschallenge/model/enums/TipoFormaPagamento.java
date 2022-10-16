package br.com.phoenix.toolschallenge.model.enums;

import br.com.phoenix.toolschallenge.resources.exceptions.ParametroInvalidoException;

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
	
	public static TipoFormaPagamento getNome(String codigo) {
		for (TipoFormaPagamento tf : values()) {
			if (tf.value.equals(codigo)) {
				return tf;
			}
		}
		throw new ParametroInvalidoException(
				"Tipo Forma Pagamento informado inválido");
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
