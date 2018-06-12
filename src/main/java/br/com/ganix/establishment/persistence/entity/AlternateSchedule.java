package br.com.ganix.establishment.persistence.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class AlternateSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7538151040811114472L;

	private long id;
	private Date distinctDay;
	private Timestamp openingTime;
	private Timestamp closingTime;
	private Establishment establishment;
	private Holiday holiday;
	private String viewerId;
	private double ticketValue;

	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDistinctDay() {
		return distinctDay;
	}

	public void setDistinctDay(Date distinctDay) {
		this.distinctDay = distinctDay;
	}

	public Timestamp getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Timestamp openingTime) {
		this.openingTime = openingTime;
	}

	public Timestamp getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Timestamp closingTime) {
		this.closingTime = closingTime;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public String getViewerId() {
		return viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public double getTicketValue() {
		return ticketValue;
	}

	public void setTicketValue(double ticketValue) {
		this.ticketValue = ticketValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((closingTime == null) ? 0 : closingTime.hashCode());
		result = prime * result
				+ ((distinctDay == null) ? 0 : distinctDay.hashCode());
		result = prime * result
				+ ((establishment == null) ? 0 : establishment.hashCode());
		result = prime * result + ((holiday == null) ? 0 : holiday.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((openingTime == null) ? 0 : openingTime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(ticketValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (!(obj instanceof AlternateSchedule))
			return false;
		AlternateSchedule other = (AlternateSchedule) obj;
		if (closingTime == null) {
			if (other.closingTime != null)
				return false;
		} else if (!closingTime.equals(other.closingTime))
			return false;
		if (distinctDay == null) {
			if (other.distinctDay != null)
				return false;
		} else if (!distinctDay.equals(other.distinctDay))
			return false;
		if (establishment == null) {
			if (other.establishment != null)
				return false;
		} else if (!establishment.equals(other.establishment))
			return false;
		if (holiday == null) {
			if (other.holiday != null)
				return false;
		} else if (!holiday.equals(other.holiday))
			return false;
		if (id != other.id)
			return false;
		if (openingTime == null) {
			if (other.openingTime != null)
				return false;
		} else if (!openingTime.equals(other.openingTime))
			return false;
		if (Double.doubleToLongBits(ticketValue) != Double
				.doubleToLongBits(other.ticketValue))
			return false;
		if (viewerId == null) {
			if (other.viewerId != null)
				return false;
		} else if (!viewerId.equals(other.viewerId))
			return false;
		return true;
	}
	
}
