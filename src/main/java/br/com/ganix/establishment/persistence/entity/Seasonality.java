package br.com.ganix.establishment.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Seasonality implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2441729258631428652L;
	
	private Establishment establishment;	
	private int days;	
	private Timestamp registryTime;

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Timestamp getRegistryTime() {
		return registryTime;
	}

	public void setRegistryTime(Timestamp registryTime) {
		this.registryTime = registryTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + days;
		result = prime * result
				+ ((establishment == null) ? 0 : establishment.hashCode());
		result = prime * result
				+ ((registryTime == null) ? 0 : registryTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Seasonality))
			return false;
		Seasonality other = (Seasonality) obj;
		if (days != other.days)
			return false;
		if (establishment == null) {
			if (other.establishment != null)
				return false;
		} else if (!establishment.equals(other.establishment))
			return false;
		if (registryTime == null) {
			if (other.registryTime != null)
				return false;
		} else if (!registryTime.equals(other.registryTime))
			return false;
		return true;
	}
	
	
}
