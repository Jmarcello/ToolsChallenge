package br.com.phoenix.toolschallenge.java;

import org.apache.commons.lang3.StringUtils;
import br.com.phoenix.toolschallenge.resources.exceptions.ParametroInvalidoException;

public class ValidadorCartaoCredito {

	private static int somaDigitos(int numero) {
		if (numero < 9) {
			return numero;
		} else {
			return numero % 10 + 1;
		}
	}

	private static boolean checkValidade(String numeroCartao) {

		int somaPar = 0;
		int somaImpar = 0;
		int aux = 0;

		// PARES
		for (int j = numeroCartao.length() - 2; j >= 0; j = j - 2) {
			aux = Integer.parseInt(numeroCartao.charAt(j) + "");
			somaPar = somaPar + somaDigitos(aux * 2);
		}

		// IMPARES
		for (int i = numeroCartao.length() - 1; i >= 0; i = i - 2) {
			aux = Integer.parseInt(numeroCartao.charAt(i) + "");
			somaImpar = somaImpar + aux;
		}

		if ((somaPar + somaImpar) % 10 == 0) {
			return true;
		} else {
			return false;
		}

	}

	private static String checkBandeiraCartao(String numero1IdEmissor, String numero2IdEmissor) {
		if (numero2IdEmissor.equals("37")) {
			return "AMERICAN EXPRESS";
		} else if (numero2IdEmissor.equals("35")) {
			return "JCB";
		} else if (numero1IdEmissor.equals("4")) {
			return "VISA";
		} else if (numero1IdEmissor.equals("5")) {
			return "MASTER";
		} else if (numero1IdEmissor.equals("6")) {
			return "DISCOVER";
		} else {
			return "OUTRA";
		}
	}

	public static boolean cartaoValido(String numeroCartao) {


		if (numeroCartao == null || StringUtils.isBlank(numeroCartao)) {
			throw new ParametroInvalidoException(
					"Cartão Inválido. Utilize para testes um cartão gerado via 4Devs ou utilize um cartão com os primeiros digitos 4444 ");
		}

		// Dentro de um Try Catch para evitar um erro
		try {

			if (numeroCartao.subSequence(0, 4).equals("4444")) {
				return true;
			}

		} catch (Exception e) {
			throw new ParametroInvalidoException(
					"Número de Cartão Inválido. Utilize para testes um cartão gerado via 4Devs ou utilize um cartão com os primeiros digitos 4444");
		}

		if (numeroCartao.length() >= 13 && numeroCartao.length() <= 16) {

			boolean flag = checkValidade(numeroCartao);

			if (flag) {

				// Cartão Válido
				checkBandeiraCartao(numeroCartao.substring(0, 1), numeroCartao.substring(0, 2));
				return true;

			} else {
				// Cartão Inválido
				// Bandeiras Aceitas: 'AMERICAN EXPRESS', 'JCB', 'VISA', 'MASTER' e 'DISCOVER'.
				// Utilize para testes um cartão gerado via 4Devs ou utilize um cartão com os
				// primeiros digitos 4444
				throw new ParametroInvalidoException(
						"Cartão Inválido. Utilize para testes um cartão gerado via 4Devs ou utilize um cartão com os primeiros digitos 4444");
			}

		} else {
			// Número de Cartão inválido
			throw new ParametroInvalidoException(
					"Número de Cartão Inválido. Utilize para testes um cartão gerado via 4Devs ou utilize um cartão com os primeiros digitos 4444");

		}

	}
}
