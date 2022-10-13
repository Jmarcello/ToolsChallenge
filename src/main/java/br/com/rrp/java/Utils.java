package br.com.rrp.java;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class Utils {

	public static String mascararTelefone(String telefone) {

		String telefoneMascarado = "(";

		String ddd;

		ddd = telefone.substring(0, 2);

		telefoneMascarado = telefoneMascarado + ddd + ") ";

		telefoneMascarado = telefoneMascarado + telefone.substring(2);

		return telefoneMascarado;

	}

	public static boolean seObjetoNulo(Object object) {

		if (object == null) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean seEnumIguais(Object enum1, Object enum2) {

		if (enum1.toString().equals(enum2.toString())) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean seBranco(String string) {

		if (StringUtils.isBlank(string)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean seValorZerado(BigDecimal valor) {

		if (valor.compareTo(BigDecimal.ZERO) < 1) {
			return true;
		} else {
			return false;
		}

	}

	public static String removeLeadingZeroes(String value) {
		return new Integer(value).toString();
	}

	public static String formatarNumeroReal(boolean agrupar, BigDecimal valor) {

		if (seObjetoNulo(valor))
			return null;

		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator(',');
		formatSymbols.setGroupingSeparator('.');

		DecimalFormat df = new DecimalFormat("#,##0.00", formatSymbols);
		df.setGroupingUsed(agrupar);

		return df.format(valor);

	}

	public static boolean isValorValido(String valor) {

		valor = valor.replaceAll(",", ".");

		try {
			@SuppressWarnings("unused")
			BigDecimal big = new BigDecimal(valor);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static String getFormatarId(Long id) {
		if (id != null)
			return String.format("%06d", id);

		return null;
	}

	public static Date getAdicionarDiaData(Date data, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DATE, +dias);
		return calendar.getTime();
	}

	public static Date getSubtrairDiaData(Date data, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DATE, -dias);
		return calendar.getTime();
	}

	public static String getFormatarFloat(Float f) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		return df.format(f);
	}

	public static String validarIntevaloDeDatas(int maxIntervalo, Date dataInicio, Date dataFim) {

		if (seObjetoNulo(dataInicio) || seObjetoNulo(dataInicio)) {
			return "Data inválida";
		}

		if (getIntervaloEntreDatas(dataInicio, dataFim) < 0) {
			return "Data final menor do que data inical";
		}

		if (getIntervaloEntreDatas(dataInicio, dataFim) > maxIntervalo) {
			return "Período excede o permitido: " + maxIntervalo + " dias";
		}

		return null;
	}

	public static int getIntervaloEntreDatas(Date data1, Date data2) {
		LocalDate ld1 = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ld2 = data2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return (int) ChronoUnit.DAYS.between(ld1, ld2);
	}

	public String getMesPorExtenso(Date data) {
		DateFormat dfmt = new SimpleDateFormat("MMMM");
		return dfmt.format(data);
	}

	public int getDia(Date data) {
		int retorno = 0;

		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		cal.setTime(data);

		retorno = cal.get(GregorianCalendar.DAY_OF_MONTH);
		cal.clear();
		cal = null;
		return retorno;

	}

	public static String NomeDoMes(int i, int tipo) {
		String mes[] = { "janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro",
				"outubro", "novembro", "dezembro" };
		if (tipo == 0)
			return (mes[i - 1]); // extenso
		else
			return (mes[i - 1].substring(0, 3)); // abreviado
	}

//		                      1 para o nome abreviado do dia da semana.
	public static String DiaDaSemana(int i, int tipo) {
		String diasem[] = { "domingo", "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira",
				"sábado" };
		if (tipo == 0)
			return (diasem[i - 1]); // extenso
		else
			return (diasem[i - 1].substring(0, 3));
	}

	// Retorna a data por extenso.
	// Parâmetros: "cidade" = nome da cidade; e, "dt" = data.
	public static String DataPorExtenso(java.util.Date dt) {
		int d = dt.getDate();
		int m = dt.getMonth() + 1;
		int a = dt.getYear() + 1900;

		// retorna o dia da semana: 1=domingo, 2=segunda-feira, ..., 7=sábado
		Calendar data = new GregorianCalendar(a, m - 1, d);
		int ds = data.get(Calendar.DAY_OF_WEEK);

		return (d + " de " + NomeDoMes(m, 0) + " de " + a + " (" + DiaDaSemana(ds, 1) + ").");
	}

	public static boolean isDataPassada(Date data) {
		LocalDate ld = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		if (ld.isAfter(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
			return true;
		else
			return false;

	}

	public static boolean isDataFutura(Date data1, Date data2) {
		LocalDate ld1 = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ld2 = data2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		if (ld1.isBefore(ld2))
			return true;
		else
			return false;

	}

	public static LocalDateTime getNow(String localZone) {
		ZoneId zone = ZoneId.of(localZone);
		ZonedDateTime now = ZonedDateTime.now(zone);

		LocalDateTime agora = now.toLocalDateTime();

		return agora;
	}

	public static String converterMinusculo(String nome) {
		String s = nome;
		String sNova = "";

		for (String sNome : s.toLowerCase().split(" ")) {
			if (!"".equals(sNome)) {
				if (!"".equals(sNova))
					sNova += " ";
				if (sNome.length() > 2) {
					sNova += sNome.substring(0, 1).toUpperCase() + sNome.substring(1);
				} else {
					sNova += sNome;
				}
			}
		}

		return sNova;

	}

	public static Date adicionarHMSHora(Date date, int hor, int min, int seg) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.add(Calendar.HOUR, hor);
		cal.add(Calendar.MINUTE, min);
		cal.add(Calendar.SECOND, seg);

		return cal.getTime();

	}

	public static Date strToDate(String strDate, String formato) {
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			return formatter.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String strDateToStrDateFormat(String data, String formatoEntrada, String formatoSaida) {

		try {

			SimpleDateFormat entrada = new SimpleDateFormat(formatoEntrada);
			SimpleDateFormat saida = new SimpleDateFormat(formatoSaida);

			Date date = entrada.parse(data);
			return (saida.format(date));

		} catch (Exception e) {
			return null;
		}

	}

	public static String dateToStr(Date date, String formato) {
		DateFormat df = new SimpleDateFormat(formato);
		return df.format(date);
	}

	public static boolean isDataValida(String strData, String formato) {

		SimpleDateFormat formatter = new SimpleDateFormat(formato);

		try {

			formatter.parse(strData);
			return true;

		} catch (ParseException e) {

			return false;

		}

	}

	public boolean isValidCNS(String cns) {
		// Validar CNS - Cartão Nacional de Saúde SUS
		if (cns.matches("[1-2]\\d{10}00[0-1]\\d") || cns.matches("[7-9]\\d{14}")) {
			return somaPonderada(cns) % 11 == 0;
		}
		return false;
	}

	private int somaPonderada(String s) {
		char[] cs = s.toCharArray();
		int soma = 0;
		for (int i = 0; i < cs.length; i++) {
			soma += Character.digit(cs[i], 10) * (15 - i);
		}
		return soma;
	}

	public static boolean isCPF(String CPF) {

		CPF = removeCaracteresEspeciais(CPF);

		if (!isNumeric(CPF))
			return (false);

		if (CPF.length() != 11)
			return (false);

		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String removeCaracteresEspeciais(String doc) {
		if (doc.contains(".")) {
			doc = doc.replace(".", "");
		}
		if (doc.contains("-")) {
			doc = doc.replace("-", "");
		}
		if (doc.contains("/")) {
			doc = doc.replace("/", "");
		}
		return doc;
	}

	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static String removerCaracteresControle(String arg) {
		return arg.replaceAll("\\s+", " ");
	}

	public static boolean isNumeric(String value) {
		return StringUtils.isNumeric(value);
	}

	public static boolean isDiaMes(String value) {
		try {
			int dia = Integer.parseInt(value);

			if (dia >= 1 && dia <= 31) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public static BigDecimal strToBigDecimal(String valor) {
		try {
			return new BigDecimal(valor);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isScientificNotation(String numberString) {

		// Validate number
		try {
			new BigDecimal(numberString);
		} catch (NumberFormatException e) {
			return false;
		}

		// Check for scientific notation
		return numberString.toUpperCase().contains("E");
	}

//	public static boolean isPercentValidator(String percentVal) {
//
//		BigDecimalValidator validator = PercentValidator.getInstance();
//
//		boolean valid = false;
//
//		BigDecimal Percent = validator.validate(percentVal, Locale.US);
//
//		if (Percent == null) {
//			valid = false;
//		}
//
//		// Check the percent is between 0% and 100%
//		if (validator.isInRange(Percent, 0, 1)) {
//			valid = true;
//		} else {
//			valid = false;
//		}
//
//		return valid;
//
//	}

	public static String getAbreviar(String arg) {
		String array[] = arg.split(" ");
		int l = array.length;
		return array[0] + " " + array[l - 1];
	}

	public static String formatarReais(BigDecimal n) {
		Locale meuLocal = new Locale("pt", "BR");
		NumberFormat nf = NumberFormat.getCurrencyInstance(meuLocal);
		return nf.format(n);
	}
}
