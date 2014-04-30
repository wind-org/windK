package org.wind.k.web.controller.tools;

import java.io.File;

public class ContentInfo {
	
	private String path;
	
	private File file;
	
	private String fileName;
	
	private int length;
	
	private String mimeType; 
	
	private long lastModified;
	
	private String etag;
	
	private boolean needCompression;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public boolean isNeedCompression() {
		return needCompression;
	}

	public void setNeedCompression(boolean needCompression) {
		this.needCompression = needCompression;
	}
	
	
}
