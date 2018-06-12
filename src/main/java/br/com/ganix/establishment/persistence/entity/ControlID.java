package br.com.ganix.establishment.persistence.entity;

import java.io.Serializable;

public class ControlID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6162623804840979501L;

	private String tableName;
	private long nextValue;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public long getNextValue() {
		return nextValue;
	}

	public void setNextValue(long nextValue) {
		this.nextValue = nextValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (nextValue ^ (nextValue >>> 32));
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ControlID))
			return false;
		ControlID other = (ControlID) obj;
		if (nextValue != other.nextValue)
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
	
	
}
