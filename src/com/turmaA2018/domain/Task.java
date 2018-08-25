package com.turmaA2018.domain;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String resume;
	private Boolean isDone;
	
	public Task() {
		super ();
		
		this.id = UUID.randomUUID().toString();
		this.isDone = false;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
	
}
