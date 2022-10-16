package br.com.phoenix.toolschallenge.java;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

import br.com.phoenix.toolschallenge.resources.exceptions.ParametroInvalidoException;

public interface ValidacaoUtils {

	static void validarNumerico(String valor, String errorMsg) {
		if (valor == null) {
			return;
		}

		try {
			new BigInteger(valor);
		} catch (NumberFormatException e) {
			throw new ParametroInvalidoException(errorMsg);
		}
	}

	static void validarTamanhoMinimo(String valor, int tamanhoMinimo, String errorMsg) {
		if (tamanhoMinimo == 0) {
			return;
		}

		if (valor == null || valor.length() < tamanhoMinimo) {
			throw new ParametroInvalidoException(errorMsg);
		}
	}

	static void validarTamanhoMaximo(String valor, int tamanhoMaximo, String errorMsg) {
		if (valor != null && valor.length() > tamanhoMaximo) {
			throw new ParametroInvalidoException(errorMsg);
		}

	}

	static void validarNaoVazio(String valor, String msg) {
		if (valor == null || StringUtils.isBlank(valor)) {
			throw new ParametroInvalidoException(msg);
		}
	}

	static void validarNaoNulo(Object valor, String msg) {
		if (valor == null) {
			throw new ParametroInvalidoException(msg);
		}
	}

	static void valorPositivo(BigDecimal valor, String msg) {
		if (valor.compareTo(BigDecimal.ZERO) == 1) {
			return;
		}

		throw new ParametroInvalidoException(msg);
	}

}
