package com.ryuseicode.siap.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @name EmailProperties
 * {@summary Properties class for SMTP }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 20, 2019
 */
@Component
@ConfigurationProperties("mail.smtp")
public class EmailProperties {
	/**
	 * Auth
	 */
	private boolean auth;
	/**
	 * ssl
	 */
	private boolean ssl;
	/**
	 * Host
	 */
	private String host;
	/**
	 * Port
	 */
	private int port;
	/**
	 * Form
	 */
	private String from;
	/**
	 * User
	 */
	private String user;
	/**
	 * Password
	 */
	private String password;
	/**
	 * Subject
	 */
	private String subject;
	/**
	 * Template
	 */
	private String template;
	/**
	 * @return the auth
	 */
	public boolean isAuth() {
		return auth;
	}
	/**
	 * @param auth the auth to set
	 */
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	/**
	 * @return the ssl
	 */
	public boolean isSsl() {
		return ssl;
	}
	/**
	 * @param ssl the ssl to set
	 */
	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
}
