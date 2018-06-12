package br.com.ganix.establishment.persistence.entity;

import java.io.Serializable;

public class Feature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7643916787705384548L;

	private long id;
	private String name;
	private String abbreviation;
	private String viewerId;

	private FeatureGroup featureGroup;

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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getViewerId() {
		return viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public FeatureGroup getFeatureGroup() {
		return featureGroup;
	}

	public void setFeatureGroup(FeatureGroup featureGroup) {
		this.featureGroup = featureGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abbreviation == null) ? 0 : abbreviation.hashCode());
		result = prime * result
				+ ((featureGroup == null) ? 0 : featureGroup.hashCode());
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
		if (!(obj instanceof Feature))
			return false;
		Feature other = (Feature) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null)
				return false;
		} else if (!abbreviation.equals(other.abbreviation))
			return false;
		if (featureGroup == null) {
			if (other.featureGroup != null)
				return false;
		} else if (!featureGroup.equals(other.featureGroup))
			return false;
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
