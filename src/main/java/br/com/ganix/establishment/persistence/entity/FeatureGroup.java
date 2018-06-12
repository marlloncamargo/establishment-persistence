package br.com.ganix.establishment.persistence.entity;

import java.io.Serializable;

public class FeatureGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5871074314313624623L;
	private long id;
	private String name;
	private String viewerId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getViewerId() {
		return viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((viewerId == null) ? 0 : viewerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FeatureGroup))
			return false;
		FeatureGroup other = (FeatureGroup) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (viewerId == null) {
			if (other.viewerId != null)
				return false;
		} else if (!viewerId.equals(other.viewerId))
			return false;
		return true;
	}
	
	
}
