package br.gov.mt.controladoria.scsp.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Functions {
	
	private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
	
	public Functions() {
	}

	public int daysBetween(Date before, Date after) {
		LocalDate beforeLocalDate = before.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate afterLocalDate = after.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return (int) java.time.temporal.ChronoUnit.DAYS.between(beforeLocalDate, afterLocalDate);
	}


	public static int daysUntilToday(Date date) {
		LocalDate dateLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();
		return (int) java.time.temporal.ChronoUnit.DAYS.between(dateLocalDate, today);
	}


	public static int getDiffHour(String value) {
		int hoursBetween = 0;

		try {
			// Converte a string para um objeto LocalTime
			LocalTime givenTime = LocalTime.parse(value);
			LocalTime currentTime = LocalTime.now();

			// Calcula o número de horas entre os dois horários
			hoursBetween = (int) ChronoUnit.HOURS.between(givenTime, currentTime);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hoursBetween;
	}

	
	public long subTrairDatas(Date dtini, Date dtfin){
		long diff = dtfin.getTime() - dtini.getTime();		
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

	public static String diferencaDias(String d1) {
		// Define o formato da data fornecida
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.forLanguageTag("pt-BR"));

		// Converte a string para LocalDateTime
		LocalDateTime dateTimeStart = LocalDateTime.parse(d1, formatter);

		// Obtém a data e hora atual na zona "America/Cuiaba"
		LocalDateTime dateTimeStop = LocalDateTime.now(ZoneId.of("America/Cuiaba"));

		// Calcula a diferença entre as datas
		Duration duration = Duration.between(dateTimeStart, dateTimeStop);

		// Extrai os componentes de dias, horas, minutos etc.
		long days = duration.toDays();
		long hours = duration.toHours() % 24;
		long minutes = duration.toMinutes() % 60;

		// Monta a string final com base na diferença
		StringBuilder output = new StringBuilder();

		if (days > 0) {
			output.append(days).append(days == 1 ? " dia" : " dias");
		}
		if (hours > 0) {
			if (output.length() > 0) output.append(", ");
			output.append(hours).append(hours == 1 ? " hora" : " horas");
		}
		if (minutes > 0) {
			if (output.length() > 0) output.append(" e ");
			output.append(minutes).append(minutes == 1 ? " minuto" : " minutos");
		}

		return output.toString();
	}

    
	public static String removeTags(String string) {
	    if (string == null || string.length() == 0) {
	        return string;
	    }

	    Matcher m = REMOVE_TAGS.matcher(string);
	    return m.replaceAll("");
	}
	
	
	public static String retornaTamanho(String size) {
		
		long tamanho = Long.parseLong(size);
		
	    if(tamanho <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(tamanho)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(tamanho/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}


	public byte[] convertBase64ToImage(String base64) {
		// Converter string Base64 para array de bytes usando java.util.Base64
		return java.util.Base64.getDecoder().decode(base64);
	}

    
	public int getDaysBetween (Timestamp start, Timestamp end)   {

        boolean negative = false;
        if (end.before(start))  {
            negative = true;
            Timestamp temp = start;
            start = end;
            end = temp;
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        GregorianCalendar calEnd = new GregorianCalendar();
        calEnd.setTime(end);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);


        if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR))   {
            if (negative)
                 return (calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR)) * -1;
            return calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
        }

        int days = 0;
        while (calEnd.after(cal))    {
            cal.add (Calendar.DAY_OF_YEAR, 1);
            days++;
        }
        if (negative)
            return days * -1;
        return days;
    }
	
	public int age(Date birthday, Date date) {
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    int d1 = Integer.parseInt(formatter.format(birthday));
	    int d2 = Integer.parseInt(formatter.format(date));
	    int age = (d2-d1)/10000;
	    return age;
	}	

}
