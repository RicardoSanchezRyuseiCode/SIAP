package com.ryuseicode.siap.wrapper.util.intf;

import java.util.Date;

/**
 * @name IDateParserWrapper
 * {@summary Interface to define the behavior of IDateParserWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 5, 2019
 */
public interface IDateParserWrapper {
	/**
	 * @name shortDate
	 * {@summary Method to translate date to string }
	 * @param date
	 * @return
	 */
	String shortDate(Date date);
	/**
	 * @name time
	 * {@summary Method to translate time to string }
	 * @param time
	 * @return
	 */
	String time(String time);
}
