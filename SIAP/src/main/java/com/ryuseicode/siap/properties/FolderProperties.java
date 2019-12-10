package com.ryuseicode.siap.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("com.ryuseicode.siap.folder")
public class FolderProperties {
	/**
	 * Upload
	 */
	private String upload;
	/**
	 * Download
	 */
	private String download;
	/**
	 * Template
	 */
	private String template;
	/**
	 * Temporal
	 */
	private String documents;
	/**
	 * @name getUpload
	 * @return
	 */
	public String getUpload() {
		return upload;
	}
	/**
	 * @name setUpload
	 * @param upload
	 */
	public void setUpload(String upload) {
		this.upload = upload;
	}
	/**
	 * @name getDownload
	 * @return
	 */
	public String getDownload() {
		return download;
	}
	/**
	 * @name SetDownload
	 * @param download
	 */
	public void setDownload(String download) {
		this.download = download;
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
	/**
	 * @return the temporal
	 */
	public String getDocuments() {
		return documents;
	}
	/**
	 * @param temporal the temporal to set
	 */
	public void setDocuments(String documents) {
		this.documents = documents;
	}

	
}
