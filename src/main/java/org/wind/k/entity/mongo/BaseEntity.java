package org.wind.k.entity.mongo;

import org.springframework.data.annotation.Id;

public abstract class BaseEntity {
	
	@Id
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
