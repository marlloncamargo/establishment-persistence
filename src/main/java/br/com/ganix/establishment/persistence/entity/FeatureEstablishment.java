package br.com.ganix.establishment.persistence.entity;

import java.io.Serializable;

public class FeatureEstablishment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2643648785751075200L;

	private long id;
	private String value;
	private Feature feature;
	private Establishment establishment;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((establishment == null) ? 0 : establishment.hashCode());
		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FeatureEstablishment))
			return false;
		FeatureEstablishment other = (FeatureEstablishment) obj;
		if (establishment == null) {
			if (other.establishment != null)
				return false;
		} else if (!establishment.equals(other.establishment))
			return false;
		if (feature == null) {
			if (other.feature != null)
				return false;
		} else if (!feature.equals(other.feature))
			return false;
		if (id != other.id)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
