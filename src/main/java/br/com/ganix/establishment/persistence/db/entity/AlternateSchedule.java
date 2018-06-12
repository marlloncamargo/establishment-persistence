package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the alternate_schedule database table.
 * 
 */
@Entity
@Table(name="alternate_schedule")
@NamedQuery(name="AlternateSchedule.findAll", query="SELECT a FROM AlternateSchedule a")
public class AlternateSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ALTERNATE_SCHEDULE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALTERNATE_SCHEDULE_ID_GENERATOR")
	private Integer id;

	@Column(name="closing_time")
	private Timestamp closingTime;

	@Temporal(TemporalType.DATE)
	@Column(name="distinct_day")
	private Date distinctDay;

	@Column(name="opening_time")
	private Timestamp openingTime;

	@Column(name="viewer_id")
	private String viewerId;

	//bi-directional many-to-one association to Establishment
	@ManyToOne
	@JoinColumn(name="establishment")
	private Establishment establishmentBean;

	//bi-directional many-to-one association to Holiday
	@ManyToOne
	@JoinColumn(name="holiday")
	private Holiday holidayBean;

	public AlternateSchedule() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getClosingTime() {
		return this.closingTime;
	}

	public void setClosingTime(Timestamp closingTime) {
		this.closingTime = closingTime;
	}

	public Date getDistinctDay() {
		return this.distinctDay;
	}

	public void setDistinctDay(Date distinctDay) {
		this.distinctDay = distinctDay;
	}

	public Timestamp getOpeningTime() {
		return this.openingTime;
	}

	public void setOpeningTime(Timestamp openingTime) {
		this.openingTime = openingTime;
	}

	public String getViewerId() {
		return this.viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public Establishment getEstablishmentBean() {
		return this.establishmentBean;
	}

	public void setEstablishmentBean(Establishment establishmentBean) {
		this.establishmentBean = establishmentBean;
	}

	public Holiday getHolidayBean() {
		return this.holidayBean;
	}

	public void setHolidayBean(Holiday holidayBean) {
		this.holidayBean = holidayBean;
	}

}