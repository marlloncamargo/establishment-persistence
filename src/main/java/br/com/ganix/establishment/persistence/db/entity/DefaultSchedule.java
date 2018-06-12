package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the default_schedule database table.
 * 
 */
@Entity
@Table(name="default_schedule")
@NamedQuery(name="DefaultSchedule.findAll", query="SELECT d FROM DefaultSchedule d")
public class DefaultSchedule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEFAULT_SCHEDULE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEFAULT_SCHEDULE_ID_GENERATOR")
	private Integer id;

	@Column(name="closing_time")
	private Timestamp closingTime;

	@Column(name="day_of_week")
	private String dayOfWeek;

	@Column(name="opening_time")
	private Timestamp openingTime;

	@Column(name="viewer_id")
	private String viewerId;

	//bi-directional many-to-one association to Establishment
	@ManyToOne
	@JoinColumn(name="establishment")
	private Establishment establishmentBean;

	public DefaultSchedule() {
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

	public String getDayOfWeek() {
		return this.dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
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

}