package br.com.rrp.resources.exceptions;

public class NaoAutorizadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NaoAutorizadoException(String mensagem) {
		super(mensagem);
	}

	public NaoAutorizadoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
