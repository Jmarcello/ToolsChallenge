package br.com.rrp.resources.exceptions;

public class ParametroInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ParametroInvalidoException(String mensagem) {
		super(mensagem);
	}

	public ParametroInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
