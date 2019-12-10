package com.ryuseicode.siap.entity.award;

/**
 * @name DocumentVariable
 * {@summary Entity class to model document variable }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
public class DocumentVariable {
	/**
	 * DocumentVariableId
	 */
	private int documentVariableId;
	/**
	 * Variable
	 */
	private String variable;
	/**
	 * ClassName
	 */
	private String className;
	/**
	 * MethodName
	 */
	private String methodName;
	/**
	 * ReturnType
	 */
	private String returnType;
	/**
	 * Active
	 */
	private int active;
	/**
	 * Default Constructor
	 * @param documentVariableId
	 * @param variable
	 * @param className
	 * @param methodName
	 * @param returnType
	 * @param active
	 */
	public DocumentVariable(int documentVariableId, String variable, String className, String methodName, String returnType, int active) {
		this.setDocumentVariableId(documentVariableId);
		this.setVariable(variable);
		this.setClassName(className);
		this.setMethodName(methodName);
		this.setReturnType(returnType);
		this.setActive(active);
	}
	/**
	 * @return the documentVariableId
	 */
	public int getDocumentVariableId() {
		return documentVariableId;
	}
	/**
	 * @param documentVariableId the documentVariableId to set
	 */
	public void setDocumentVariableId(int documentVariableId) {
		this.documentVariableId = documentVariableId;
	}
	/**
	 * @return the variable
	 */
	public String getVariable() {
		return variable;
	}
	/**
	 * @param variable the variable to set
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * @return the returnType
	 */
	public String getReturnType() {
		return returnType;
	}
	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}
}
