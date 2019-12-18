package com.ryuseicode.siap.wrapper.util.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.ryuseicode.siap.wrapper.util.intf.IDateParserWrapper;

@Service
public class DateParserWrapper extends ParserWrapper implements IDateParserWrapper {
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
