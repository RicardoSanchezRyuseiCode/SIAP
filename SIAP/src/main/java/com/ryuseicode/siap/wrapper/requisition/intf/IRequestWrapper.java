package com.ryuseicode.siap.wrapper.requisition.intf;
/**
 * @name IRequestWrapper
 * {@summary Interface to define the behavior of IRequestWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 23, 2019
 */
public interface IRequestWrapper {
	/**
	 * @name close
	 * {@summary Method to close request }
	 * @param requestId
	 * @throws Exception
	 */
	void close(int requestId) throws Exception;
}
