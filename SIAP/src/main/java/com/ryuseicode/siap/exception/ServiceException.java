package com.ryuseicode.siap.exception;

/**
 * @name ServiceException
 * {@summary Class to define custom exception}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
public class ServiceException extends Exception {
    
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @name ServiceException
	 * {@summary Default Constructor}
	 * @param message
	 */
	public ServiceException(String message) {
        super(message);
        
    }    
}