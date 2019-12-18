package com.ryuseicode.siap.wrapper.util.imp;

import org.springframework.stereotype.Service;

import com.ryuseicode.siap.wrapper.util.intf.INumberParserWrapper;
/**
 * @name NumberParserWrapper
 * {@summary Wrapper to parser numbers }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
@Service
public class NumberParserWrapper extends ParserWrapper implements INumberParserWrapper {
	/**
	 * @name doubleToCurrencyText
	 * {@summary Method to parse double number to currency text }
	 * @param number
	 * @return
	 */
	public String doubleToCurrencyText(double number) {
		// Get String represetnation
		String numberStr = String.format("%.2f", number);
		// Split the number
		String[] numberStrSplit = numberStr.split("\\.");
		// Parse number
		String afterPoint = numberToText(Integer.parseInt(numberStrSplit[0]));
		String beforePoint = numberToText(Integer.parseInt(numberStrSplit[1]));
		// Join text
		return String.format("%s pesos / %s centavos moneda nacional", afterPoint, beforePoint);
		
	}
}
