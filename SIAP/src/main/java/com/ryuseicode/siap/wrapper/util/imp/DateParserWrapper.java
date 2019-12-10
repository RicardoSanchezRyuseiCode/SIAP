package com.ryuseicode.siap.wrapper.util.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.ryuseicode.siap.wrapper.util.intf.IDateParserWrapper;

@Service
public class DateParserWrapper implements IDateParserWrapper {
	/**
	 * UNIT
	 */
	private final static String[] UNITS = {"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve","diez","once","doce","trece","catorce","quince" };
	/**
	 * TENS
	 */
	private final static String[] TENS = {"","","veinte","treinta","cuarenta","cincuenta","sesenta","setenta","ochenta","noventa"};
	/**
	 * @name numberToText
	 * {@summary Method to parse number to text }
	 * @param i
	 * @return
	 */
	private String numberToText(int i) {
		if( i < 16)  return UNITS[i];
		if( i < 20) return TENS[i/10] + ((i % 10 > 0)? "dieci" + numberToText(i % 10):"");
        if( i == 20) return "veinte";
		if( i < 30) return ((i % 10 > 0)? "veinti" + numberToText(i % 10):"");
        if( i == 30) return "treinta";
        if( i < 40) return ((i % 10 > 0)? "treinta y " + numberToText(i % 10):"");
        if( i == 40) return "cuarenta";
        if( i < 50) return ((i % 10 > 0)? "cuarenta y " + numberToText(i % 10):"");
        if( i == 50) return "cincuenta";
        if( i < 60) return ((i % 10 > 0)? "cincuenta y " + numberToText(i % 10):"");
        if( i == 60) return "sesenta";
        if( i < 70) return ((i % 10 > 0)? "sesenta y " + numberToText(i % 10):"");
        if( i == 70) return "setenta";
        if( i < 80) return ((i % 10 > 0)? "setenta y " + numberToText(i % 10):"");
        if( i == 80) return "ochenta";
        if( i < 90) return ((i % 10 > 0)? "ochenta y " + numberToText(i % 10):"");
        if( i == 90) return "noventa";
        if( i < 100) return ((i % 10 > 0)? "noventa y " + numberToText(i % 10):"");
        if( i == 100) return "cien";
        if( i < 200) return "ciento" + ((i % 100 > 0)?" " + numberToText(i % 100):"");        
        if( i < 500) return UNITS[i/100] + "cientos" + ((i % 100 > 0)?" " + numberToText(i % 100):"");        
        if( i < 600) return "quinientos" + ((i % 100 > 0)?" " + numberToText(i % 100):"");                        
        if( i < 900) return UNITS[i/100] + "cientos" + ((i % 100 > 0)?" " + numberToText(i % 100):"");        
        if( i < 1000) return "novecientos" + ((i % 100 > 0)?" " + numberToText(i % 100):"");                
		if( i < 2000) return "mil" + ((i % 1000 > 0)? " " + numberToText(i % 1000):"") ;                
        if( i < 1000000) return numberToText(i / 1000) + " mil" + ((i % 1000 > 0)? " " + numberToText(i % 1000):"");		
        return numberToText(i / 1000000) + " millon " + ((i % 1000000 > 0)? " " + numberToText(i % 1000000):"") ;
	}
	/**
	 * @name shortDate
	 * {@summary Method to translate date to string }
	 * @param date
	 * @return
	 */
	public String shortDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd '(%s) de' MMMM 'de' yyyy '(%s)'", new Locale("es","MX"));
		String strDate= formatter.format(date);
		Calendar c = Calendar.getInstance();
        c.setTime(date);
		return String.format(strDate, numberToText(c.get(Calendar.DAY_OF_MONTH)), numberToText(c.get(Calendar.YEAR)));
	}
	/**
	 * @name time
	 * {@summary Method to translate time to string }
	 * @param time
	 * @return
	 */
	public String time(String time) {
		// Split time string
		String[] split = time.split(":");
		// Comvert number to string
		String hours = numberToText(Integer.parseInt(split[0])); 
		String minutes = numberToText(Integer.parseInt(split[1]));
		// format and return string
		return String.format("%s (%s horas, %s minutos)", time, hours, minutes);
	}
}
