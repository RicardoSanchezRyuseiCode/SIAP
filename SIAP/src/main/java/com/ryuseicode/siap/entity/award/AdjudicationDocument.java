package com.ryuseicode.siap.entity.award;
/**
 * @name AdjudicationDocument
 * {@summary Entity class to model adjudication document }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public class AdjudicationDocument {
	/**
	 * adjudicationDocumentId
	 */
	private int adjudicationDocumentId;
	/**
	 * adjudicationId
	 */
	private int adjudicationId;
	/**
	 * identifier
	 */
	private String identifier;
	/**
	 * name
	 */
	private String name;
	/**
	 * Path
	 */
	private String path;
	/**
	 * Default constructor
	 * @param adjudicationDocumentId
	 * @param adjudicationId
	 * @param identifier
	 * @param name
	 * @param path
	 */
	public AdjudicationDocument(int adjudicationDocumentId, int adjudicationId, String identifier, String name, String path) {
		this.setAdjudicationDocumentId(adjudicationDocumentId);
		this.setAdjudicationId(adjudicationId);
		this.setIdentifier(identifier);
		this.setName(name);
		this.setPath(path);
	}
	/**
	 * @return the adjudicationDocumentId
	 */
	public int getAdjudicationDocumentId() {
		return adjudicationDocumentId;
	}
	/**
	 * @param adjudicationDocumentId the adjudicationDocumentId to set
	 */
	public void setAdjudicationDocumentId(int adjudicationDocumentId) {
		this.adjudicationDocumentId = adjudicationDocumentId;
	}
	/**
	 * @return the adjudicationId
	 */
	public int getAdjudicationId() {
		return adjudicationId;
	}
	/**
	 * @param adjudicationId the adjudicationId to set
	 */
	public void setAdjudicationId(int adjudicationId) {
		this.adjudicationId = adjudicationId;
	}
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}	
}
