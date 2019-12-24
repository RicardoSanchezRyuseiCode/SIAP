package com.ryuseicode.siap.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @name DistributionProperties
 * {@summary Distribution properties for application }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 20, 2019
 */
@Component
@ConfigurationProperties("com.ryuseicode.siap.mail.distribution")
public class DistributionProperties {
	/**
	 * CC
	 */
	private String cc;
	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	
}
