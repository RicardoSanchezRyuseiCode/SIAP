package com.ryuseicode.siap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @name SiapApplication
 * {@summary Class to define entry point of application}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-19
 */
@SpringBootApplication
public class SiapApplication {
	/**
	 * @name main
	 * {@summary Method to define entry point of application}
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SiapApplication.class, args);
	}

}
