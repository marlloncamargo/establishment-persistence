package br.com.ganix.establishment.persistence.entity;

import java.io.Serializable;
import java.util.List;

public class Establishment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1471510912904667720L;

	private long id;
	private long documentNumber;
	private String companyName;
	private String fantasyName;
	private String description;
	private long quantityLimit;
	private List<EstablishmentTypeEstablishment> establishmentTypes;
	private String viewerId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}
	
	public long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(long documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getQuantityLimit() {
		return quantityLimit;
	}

	public void setQuantityLimit(long quantityLimit) {
		this.quantityLimit = quantityLimit;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public String getViewerId() {
		return viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public List<EstablishmentTypeEstablishment> getEstablishmentTypes() {
		return establishmentTypes;
	}

	public void setEstablishmentTypes(
			List<EstablishmentTypeEstablishment> establishmentTypes) {
		this.establishmentTypes = establishmentTypes;
	}

	@Override
	public String toString() {
		return "Establishment [documentNumber=" + documentNumber + ", companyName=" + companyName
				+ ", fantasyName=" + fantasyName + ", quantityLimit=" + quantityLimit 
				+ ", viewerId=" + viewerId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ (int) (documentNumber ^ (documentNumber >>> 32));
		result = prime
				* result
				+ ((establishmentTypes == null) ? 0 : establishmentTypes
						.hashCode());
		result = prime * result
				+ ((fantasyName == null) ? 0 : fantasyName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ (int) (quantityLimit ^ (quantityLimit >>> 32));
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
		if (!(obj instanceof Establishment))
			return false;
		Establishment other = (Establishment) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (documentNumber != other.documentNumber)
			return false;
		if (establishmentTypes == null) {
			if (other.establishmentTypes != null)
				return false;
		} else if (!establishmentTypes.equals(other.establishmentTypes))
			return false;
		if (fantasyName == null) {
			if (other.fantasyName != null)
				return false;
		} else if (!fantasyName.equals(other.fantasyName))
			return false;
		if (id != other.id)
			return false;
		if (quantityLimit != other.quantityLimit)
			return false;
		if (viewerId == null) {
			if (other.viewerId != null)
				return false;
		} else if (!viewerId.equals(other.viewerId))
			return false;
		return true;
	}	
}
