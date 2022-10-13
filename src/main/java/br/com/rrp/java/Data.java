package br.com.rrp.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class Data {

	public static String getData(String formato) {
		Date data = new Date();
		return DateFormatUtils.format(data, formato);
	}

	public static boolean isValida(String data, String formato) {

		if (data == null || StringUtils.isEmpty(data)) {
			return false;
		}

		DateFormat sdf = new SimpleDateFormat(formato);
		sdf.setLenient(false);

		try {
			sdf.parse(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String dateToStr(Date date, String formato) {
		try {
			DateFormat df = new SimpleDateFormat(formato);
			return df.format(date);
		} catch (Exception e) {
			return null;
		}

	}

	public static String adicionarDia(int qtd) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		calendar.add(Calendar.DATE, +qtd);
		Date novoVencimento = calendar.getTime();
		return sdf.format(novoVencimento);
	}

	public static String adicionarDia(String data, int qtd) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		int dia;

		if (data.substring(0, 1).equals("0")) {
			dia = Integer.valueOf(data.substring(1, 2));
		} else {
			dia = Integer.valueOf(data.substring(0, 2));
		}

		int mes;

		if (data.substring(3, 4).equals("0")) {
			mes = Integer.valueOf(data.substring(4, 5));
		} else {
			mes = Integer.valueOf(data.substring(3, 5));
		}

		int ano = Integer.valueOf(data.substring(6, 10));

		LocalDate localDate = LocalDate.of(ano, mes, dia);

		LocalDate ret = localDate.plusDays(qtd);
		return ret.format(formatter);
	}

	public static boolean isDataVencida(String dataVencimento) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

		boolean ret = false;

		try {
			Date dtVencimento = sdf.parse(dataVencimento);
			Date dtHoje = sdf.parse(Data.agora());

			int i = dtHoje.compareTo(dtVencimento);

			switch (i) {
			case 0:
				ret = false;
				break;
			case -1:
				ret = false;
				break;
			default:
				// 1
				ret = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return ret;

	}

	public static String agora() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		return currentTime;
	}

	public static String agoraBrasilFormat() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String currentTime = sdf.format(dt);
		return currentTime;
	}

	public static String getDataHoraStr(String dataHora) {
		String time = "yyyy-MM-dd HH:mm:ss";

		String dia = dataHora.substring(0, 2);
		String mes = dataHora.substring(3, 5);
		String ano = dataHora.substring(6, 10);

		time = time.replace("dd", dia);
		time = time.replace("MM", mes);
		time = time.replace("yyyy", ano);

		String hor = "";
		String min = "";
		String seg = "";

		if (dataHora.length() == 10) {
			hor = "00";
			min = "00";
			seg = "00";
		} else {
			hor = dataHora.substring(11, 13);
			min = dataHora.substring(14, 16);
			seg = dataHora.substring(17, 19);
		}

		time = time.replace("HH", hor);
		time = time.replace("mm", min);
		time = time.replace("ss", seg);

		return time;
	}

	public static String getDataStrBR(String dataHora) {
		String time = "dd/MM/yyyy";

		String dia = dataHora.substring(0, 2);
		String mes = dataHora.substring(3, 5);
		String ano = dataHora.substring(6, 10);

		time = time.replace("dd", dia);
		time = time.replace("MM", mes);
		time = time.replace("yyyy", ano);

		return time;
	}

	public static String getDataStrUSA(String dataHora) {
		String time = "yyyy-MM-dd";

		String dia = dataHora.substring(0, 2);
		String mes = dataHora.substring(3, 5);
		String ano = dataHora.substring(6, 10);

		time = time.replace("dd", dia);
		time = time.replace("MM", mes);
		time = time.replace("yyyy", ano);

		return time;
	}

	public static Date strToDate(String strDate, String formato) {
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			return formatter.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}
}
