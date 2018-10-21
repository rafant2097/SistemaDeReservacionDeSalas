package logicaDeNegocio;

import java.time.LocalDateTime;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeManager {
	
	public static String constructDate(String pDate, String pTime) {
		
		return pDate + " " + pTime.replace(".", ":") + ":00";
		
		
	}
	
	//Si da 1, ya la fecha recibida pasó
	public static int compareDateWithToday(String pDate) {
		
			LocalDateTime today = LocalDateTime.now();
			
			LocalDateTime dateToCompare = LocalDateTime.parse(pDate);
			
			return dateToCompare.compareTo(today);
			
		}
		
	public static String getDateOnly(String pDate) {
		
		return pDate.substring(0, 10);
		
	}
	
	public static String getHourOnly(String pDate) {
		
		return pDate.substring(pDate.length()-8, pDate.length());
		
	}
	
	public static String fixHour(String pDate) {
		
		
		int hour = Integer.parseInt(pDate.substring(11, 13));
		hour += 6;
		String inicio = pDate.substring(0,11);
		
		if(hour==25) {
			hour=01;
			inicio += "0";
		}
		else if(hour==26) {
			hour=02;
			inicio += "0";
		}
		return  inicio + hour + pDate.substring(13,pDate.length());
		
		
	}
	
	public static boolean isBetween(String pDateStart, String pDateEnd, String pDateCompareStart, String pDateCompareEnd) {
		
		try {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss");
		
		DateTime fechaInicio1 = formatter.parseDateTime(pDateStart);
		DateTime fechaFin1 = formatter.parseDateTime(pDateEnd);

		DateTime fechaInicio2 = formatter.parseDateTime(pDateCompareStart);
		DateTime fechaFin2 = formatter.parseDateTime(pDateCompareEnd);

		Interval intervalo1 = new Interval( fechaInicio1, fechaFin1 );
		Interval intervalo2 = new Interval( fechaInicio2, fechaFin2 );
		
		
			
			return intervalo2.overlaps(intervalo1);
			
		}catch(Exception e) {
			
			return false;
			
		}
		
		
	}
	
}
