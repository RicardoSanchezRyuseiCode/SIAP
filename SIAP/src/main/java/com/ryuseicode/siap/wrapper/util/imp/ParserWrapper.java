package com.ryuseicode.siap.wrapper.util.imp;

public class ParserWrapper {
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
	protected String numberToText(int i) {
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
}
