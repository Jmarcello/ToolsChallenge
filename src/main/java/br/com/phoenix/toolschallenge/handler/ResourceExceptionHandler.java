package br.com.phoenix.toolschallenge.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.phoenix.toolschallenge.resources.exceptions.DetalhesErro;
import br.com.phoenix.toolschallenge.resources.exceptions.ParametroInvalidoException;
import br.com.phoenix.toolschallenge.resources.exceptions.RecursoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

//Criado Handler para tratamento de Erro	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException e,
			HttpServletRequest request) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetalhesErro(e.getMessage(), 404l, 404l,
				System.currentTimeMillis(), "http://localhost:8080/erros/404"));
	}

	@ExceptionHandler(ParametroInvalidoException.class)
	public ResponseEntity<DetalhesErro> handleParametroInvalidoException(ParametroInvalidoException e,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new DetalhesErro(e.getMessage(), 406l, 406l,
				System.currentTimeMillis(), "https://localhost:8080/erros/406"));
	}

	
}
