package com.ryuseicode.siap;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @name ServletInitializer
 * {@summary Class to define the entry point of Servlets}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-19
 */
public class ServletInitializer extends SpringBootServletInitializer {
	/**
	 * @name configure
	 * {@summary Method to set entry point of servlets}
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SiapApplication.class);
	}
}
