package br.com.phoenix.toolschallenge.resources.exceptions;

public class ErroInternoServidorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErroInternoServidorException(String mensagem) {
		super(mensagem);
	}

	public ErroInternoServidorException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
