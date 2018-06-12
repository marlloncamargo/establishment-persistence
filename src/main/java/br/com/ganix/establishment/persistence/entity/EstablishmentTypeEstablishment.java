package br.com.ganix.establishment.persistence.entity;

import java.io.Serializable;

public class EstablishmentTypeEstablishment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8147526633358227211L;

	private long id;
	private Establishment establishment;
	private EstablishmentType establishmentType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public EstablishmentType getEstablishmentType() {
		return establishmentType;
	}

	public void setEstablishmentType(EstablishmentType establishmentType) {
		this.establishmentType = establishmentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((establishment == null) ? 0 : establishment.hashCode());
		result = prime
				* result
				+ ((establishmentType == null) ? 0 : establishmentType
						.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EstablishmentTypeEstablishment))
			return false;
		EstablishmentTypeEstablishment other = (EstablishmentTypeEstablishment) obj;
		if (establishment == null) {
			if (other.establishment != null)
				return false;
		} else if (!establishment.equals(other.establishment))
			return false;
		if (establishmentType == null) {
			if (other.establishmentType != null)
				return false;
		} else if (!establishmentType.equals(other.establishmentType))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
