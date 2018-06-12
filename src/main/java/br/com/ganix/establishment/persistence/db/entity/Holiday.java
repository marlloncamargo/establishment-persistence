package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the holiday database table.
 * 
 */
@Entity
@NamedQuery(name="Holiday.findAll", query="SELECT h FROM Holiday h")
public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HOLIDAY_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HOLIDAY_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="holiday_date")
	private Date holidayDate;

	private String name;

	@Column(name="viewer_id")
	private String viewerId;

	//bi-directional many-to-one association to AlternateSchedule
	@OneToMany(mappedBy="holidayBean")
	private List<AlternateSchedule> alternateSchedules;

	public Holiday() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getHolidayDate() {
		return this.holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getViewerId() {
		return this.viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public List<AlternateSchedule> getAlternateSchedules() {
		return this.alternateSchedules;
	}

	public void setAlternateSchedules(List<AlternateSchedule> alternateSchedules) {
		this.alternateSchedules = alternateSchedules;
	}

	public AlternateSchedule addAlternateSchedule(AlternateSchedule alternateSchedule) {
		getAlternateSchedules().add(alternateSchedule);
		alternateSchedule.setHolidayBean(this);

		return alternateSchedule;
	}

	public AlternateSchedule removeAlternateSchedule(AlternateSchedule alternateSchedule) {
		getAlternateSchedules().remove(alternateSchedule);
		alternateSchedule.setHolidayBean(null);

		return alternateSchedule;
	}

}