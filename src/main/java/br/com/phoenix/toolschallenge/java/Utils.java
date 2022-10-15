package br.com.phoenix.toolschallenge.java;

import java.util.Random;

public class Utils {

	public static String gerarSequenciaNumerica(Integer tamanho) {
		
		if(tamanho == null) {
			return null;
		}

		StringBuilder sequencia = new StringBuilder();
		Random random = new Random();
		int number;

		for (int i = 0; i < tamanho; i++) {

			// gera um número aleatório de 0 a 9
			number = random.nextInt(10);

			sequencia.append(number);
		}
		return sequencia.toString();
	}

}
