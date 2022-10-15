package br.com.phoenix.toolschallenge.java;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import br.com.phoenix.toolschallenge.resources.exceptions.ParametroInvalidoException;
import br.com.phoenix.toolschallenge.resources.exceptions.RecursoNaoEncontradoException;

public interface ValidacaoUtils {

	static final String REGEX_HORARIO_SEM_SEGUNDOS = "^(([01]\\d:[0-5][0-9])|(2[0-3]:[0-5][0-9]))$";

	static final String REGEX_HORARIO = "^(([01]\\d:[0-5][0-9](:[0-5][0-9])?)|(2[0-3]:[0-5][0-9](:[0-5][0-9])?))$";

	Pattern PATTERN_LETRA = Pattern.compile("[A-Za-z]");
	Pattern PATTERN_NUMERO = Pattern.compile("\\d");
	int CNPJ_QNT_CHARS = 14;
	int CPF_QNT_CHARS = 11;

	static void validarRegex(String value, String regex, String errorMsg) {
		if (value == null || !value.matches(regex)) {
			throw new ParametroInvalidoException(errorMsg);
		}
	}

	/**
	 * Verifica se o valor é numérico. O valor null também é considerado numérico.
	 * 
	 * @param valor    valor a ser validado
	 * @param errorMsg mensagem de erro da exception caso o valor não seja numérico
	 * @throws ParametroInvalidoException caso o valor não seja numérico
	 */
	static void validarNumerico(String valor, String errorMsg) {
		if (valor == null) {
			return;
		}

		try {
			new BigDecimal(valor);
		} catch (NumberFormatException e) {
			throw new ParametroInvalidoException(errorMsg);
		}
	}

	/**
	 * Verifica se o valor é numérico inteiro. O valor null também é considerado
	 * numérico.
	 * 
	 * @param valor    valor a ser validado
	 * @param errorMsg mensagem de erro da exception caso o valor não seja numérico
	 * @throws ParametroInvalidoException caso o valor não seja numérico
	 */
	static void validarNumericoInteiro(String valor, String errorMsg) {
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

	static String builMensagemTamanho(String artigoNomeCampo, String nomeCampo, int tamanhoMaximo) {
		return String.format("%s %s deve ter no máximo %s caracteres", artigoNomeCampo, nomeCampo, tamanhoMaximo);
	}

	static String builMensagemTamanho(String nomeCampo, int tamanhoMaximo) {
		return String.format("%s deve ter no máximo %s caracteres", nomeCampo, tamanhoMaximo);
	}

	static void validarCnpj(String valor, String errorMsg) {
		if (valor == null) {
			return;
		}
		valor = valor.replaceAll("\\D", "");
		if (valor.length() != CNPJ_QNT_CHARS) {
			throw new ParametroInvalidoException(errorMsg);
		}
	}

	static void validarData(String valor, String formato, String errorMsg) {
		DateFormat sdf = new SimpleDateFormat(formato);
		sdf.setLenient(false);
		try {
			sdf.parse(valor);
		} catch (ParseException e) {
			throw new ParametroInvalidoException(errorMsg);
		}
	}

	static void validarNaoVazio(Object valor, String msg) {
		if (valor == null) {
			throw new ParametroInvalidoException(msg);
		}
	}

	/**
	 * Valida se a String não é nula, vazia ou está em branco.
	 * 
	 * @param valor valor a ser validado
	 * @param msg   mensagem da exception que será lançada caso o valo seja nulo
	 * @throws ParametroInvalidoException caso o valor seja nulo, vazio ou em branco
	 */
	static void validarNaoVazio(String valor, String msg) {
		if (valor == null || StringUtils.isBlank(valor)) {
			throw new ParametroInvalidoException(msg);
		}
	}

	/**
	 * Valida se a String não é nula, vazia ou está em branco.
	 * 
	 * @param valor valor a ser validado
	 * @param msg   mensagem da exception que será lançada caso o valor seja nulo ou
	 *              vazio
	 * @throws RecursoNaoEncontradoException caso o valor seja nulo, vazio ou em
	 *                                       branco
	 */
	static void validarNaoVazio404(String valor, String msg) {
		if (StringUtils.isBlank(valor)) {
			throw new RecursoNaoEncontradoException(msg);
		}
	}

	/**
	 * Valida se o Iterabl não é nulo ou está vazio.
	 * 
	 * @param valor valor a ser validado
	 * @param msg   mensagem da exception que será lançada caso o valo seja nulo ou
	 *              vazio
	 * @throws ParametroInvalidoException caso o valor seja nulo ou vazio
	 */
	static void validarNaoVazio(Iterable<?> valor, String msg) {
		if (valor == null || !valor.iterator().hasNext()) {
			throw new ParametroInvalidoException(msg);
		}
	}

	/**
	 * Valida se o Iterable não é nulo ou está vazio.
	 * 
	 * @param valor valor a ser validado
	 * @param msg   mensagem da exception que será lançada caso o valo seja nulo ou
	 *              vazio
	 * @throws RecursoNaoEncontradoException caso o valor seja nulo ou vazio
	 */
	static void validarNaoVazio404(Iterable<?> valor, String msg) {
		if (valor == null || !valor.iterator().hasNext()) {
			throw new RecursoNaoEncontradoException(msg);
		}
	}

	static boolean validarNaoVazio(Iterable<?> valor) {
		return valor != null && valor.iterator().hasNext();
	}

	static boolean validarNaoVazio(String valor) {
		return !StringUtils.isBlank(valor);
	}

	/**
	 * Valida se o valor não é nulo.
	 * 
	 * @param valor valor a ser validado
	 * @param msg   mensagem da exception que será lançada caso o valo seja nulo
	 * @throws ParametroInvalidoException caso o valor seja nulo
	 */
	static void validarNaoNulo(Object valor, String msg) {
		if (valor == null) {
			throw new ParametroInvalidoException(msg);
		}
	}

	/**
	 * Valida se o valor não é nulo.
	 * 
	 * @param valor valor a ser validado
	 * @param msg   mensagem da exception que será lançada caso o valo seja nulo
	 * @throws RecursoNaoEncontradoException caso o valor seja nulo
	 */
	static void validarNaoNulo404(Object valor, String msg) {
		if (valor == null) {
			throw new RecursoNaoEncontradoException(msg);
		}
	}

	static boolean validarNaoNulo(Object valor) {
		return valor != null;
	}

	static void validarTrue(Boolean bool, String msg) {
		if (Boolean.TRUE.equals(bool)) {
			return;
		}
		throw new ParametroInvalidoException(msg);
	}

	static void validarFalse(Boolean bool, String msg) {
		if (Boolean.FALSE.equals(bool)) {
			return;
		}
		throw new ParametroInvalidoException(msg);
	}

	static <T extends Comparable<T>> void validarMinimo(T valor, T minimo, String msg) {
		if (valor == null) {
			throw new ParametroInvalidoException(msg);
		}

		if (valor.compareTo(minimo) < 0) {
			throw new ParametroInvalidoException(msg);
		}
	}

	static <T extends Comparable<T>> boolean validarMinimo(T valor, T minimo) {
		return valor != null && valor.compareTo(minimo) >= 0;
	}

	static <T extends Comparable<T>> void validarMinimo(T valor, T minimo, String msg, boolean ignorarNulo) {
		if (ignorarNulo) {
			return;
		}
		validarMinimo(valor, minimo, msg);
	}

	static <T extends Comparable<T>> boolean validarMinimo(T valor, T minimo, boolean ignorarNulo) {
		if (ignorarNulo) {
			return true;
		}
		return validarMinimo(valor, minimo);
	}

	static <T extends Comparable<T>> void validarMaximo(T valor, T maximo, String msg) {
		if (valor == null) {
			throw new ParametroInvalidoException(msg);
		}

		// se maior que
		// lançar exception
		if (valor.compareTo(maximo) > 0) {
			throw new ParametroInvalidoException(msg);
		}
	}

	static <T extends Comparable<T>> boolean validarMaximo(T valor, T maximo) {
		return valor != null && valor.compareTo(maximo) <= 0;
	}

	static <T extends Comparable<T>> void validarMaximo(T valor, T maximo, String msg, boolean ignorarNulo) {
		if (ignorarNulo) {
			return;
		}
		validarMaximo(valor, maximo, msg);
	}

	static <T extends Comparable<T>> boolean validarMaximo(T valor, T minimo, boolean ignorarNulo) {
		if (ignorarNulo) {
			return true;
		}
		return validarMaximo(valor, minimo);
	}

	static void validarAoMenosUmNaoVazio(String msg, Object... valores) {
		for (Object valor : valores) {
			boolean isString = valor instanceof String;
			boolean isIterable = valor instanceof Iterable;
			if (isString && !StringUtils.isBlank((String) valor)) {
				return;
			} else if (isIterable && validarNaoVazio((Iterable<?>) valor)) {
				return;
			} else if (!isString && !isIterable && valor != null) {
				return;
			}
		}
		throw new ParametroInvalidoException(msg);
	}

	static void validarAoMenosUmNaoNulo(String msg, Object... valores) {
		for (Object valor : valores) {
			if (valor != null) {
				return;
			}
		}
		throw new ParametroInvalidoException(msg);
	}

	static boolean isAoMenosUmNaoNulo(Object... valores) {
		for (Object valor : valores) {
			if (valor != null) {
				return true;
			}
		}
		return false;
	}

	static void validarSenhaPadrao(String senha) {
		validarNaoNulo(senha, "O campo Senha deve ser informado");
		validarMaximo(senha.length(), 128, "A senha não pode ser maior que 128 caracteres");
		validarMinimo(senha.length(), 8, "A senha deve possuir ao menos 8 caracteres");
		validarTrue(PATTERN_NUMERO.matcher(senha).find(), "A senha deve possuir ao menos um número");
		validarTrue(PATTERN_LETRA.matcher(senha).find(), "A senha deve possuir ao menos uma letra");
	}

	static void validarIguais(Object obj1, Object obj2, String msg) {
		validarTrue(Objects.equals(obj1, obj2), msg);
	}

	static void validarDiferentes(Object obj1, Object obj2, String msg) {
		validarTrue(!Objects.equals(obj1, obj2), msg);
	}

}
