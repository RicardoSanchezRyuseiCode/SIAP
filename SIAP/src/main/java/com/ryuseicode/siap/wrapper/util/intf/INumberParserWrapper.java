package com.ryuseicode.siap.wrapper.util.intf;
/**
 * @name INumberParser
 * {@summary Interface to define the behavior of INumberParser }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
public interface INumberParserWrapper {
	/**
	 * @name doubleToCurrencyText
	 * {@summary Method to parse double number to currency text }
	 * @param number
	 * @return
	 */
	String doubleToCurrencyText(double number);
}
