package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the establishment database table.
 * 
 */
@Entity
@NamedQuery(name="Establishment.findAll", query="SELECT e FROM Establishment e")
public class Establishment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTABLISHMENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTABLISHMENT_ID_GENERATOR")
	private Long id;

	private Long cnpj;

	@Column(name="company_name")
	private String companyName;

	@Column(name="fantasy_name")
	private String fantasyName;

	private double latitude;

	private double longitude;

	@Column(name="man_quantity")
	private Long manQuantity;

	@Column(name="quantity_limit")
	private Long quantityLimit;

	private double ticketvalue;

	@Column(name="viewer_id")
	private String viewerId;

	@Column(name="woman_quantity")
	private Long womanQuantity;

	//bi-directional many-to-one association to AlternateSchedule
	@OneToMany(mappedBy="establishmentBean")
	private List<AlternateSchedule> alternateSchedules;

	//bi-directional many-to-one association to DefaultSchedule
	@OneToMany(mappedBy="establishmentBean")
	private List<DefaultSchedule> defaultSchedules;

	//bi-directional many-to-one association to EstablishmentEstablishmentType
	@OneToMany(mappedBy="establishmentBean")
	private List<EstablishmentEstablishmentType> establishmentEstablishmentTypes;

	//bi-directional many-to-one association to FeatureEstablishment
	@OneToMany(mappedBy="establishmentBean")
	private List<FeatureEstablishment> featureEstablishments;

	public Establishment() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFantasyName() {
		return this.fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Long getManQuantity() {
		return this.manQuantity;
	}

	public void setManQuantity(Long manQuantity) {
		this.manQuantity = manQuantity;
	}

	public Long getQuantityLimit() {
		return this.quantityLimit;
	}

	public void setQuantityLimit(Long quantityLimit) {
		this.quantityLimit = quantityLimit;
	}

	public double getTicketvalue() {
		return this.ticketvalue;
	}

	public void setTicketvalue(double ticketvalue) {
		this.ticketvalue = ticketvalue;
	}

	public String getViewerId() {
		return this.viewerId;
	}

	public void setViewerId(String viewerId) {
		this.viewerId = viewerId;
	}

	public Long getWomanQuantity() {
		return this.womanQuantity;
	}

	public void setWomanQuantity(Long womanQuantity) {
		this.womanQuantity = womanQuantity;
	}

	public List<AlternateSchedule> getAlternateSchedules() {
		return this.alternateSchedules;
	}

	public void setAlternateSchedules(List<AlternateSchedule> alternateSchedules) {
		this.alternateSchedules = alternateSchedules;
	}

	public AlternateSchedule addAlternateSchedule(AlternateSchedule alternateSchedule) {
		getAlternateSchedules().add(alternateSchedule);
		alternateSchedule.setEstablishmentBean(this);

		return alternateSchedule;
	}

	public AlternateSchedule removeAlternateSchedule(AlternateSchedule alternateSchedule) {
		getAlternateSchedules().remove(alternateSchedule);
		alternateSchedule.setEstablishmentBean(null);

		return alternateSchedule;
	}

	public List<DefaultSchedule> getDefaultSchedules() {
		return this.defaultSchedules;
	}

	public void setDefaultSchedules(List<DefaultSchedule> defaultSchedules) {
		this.defaultSchedules = defaultSchedules;
	}

	public DefaultSchedule addDefaultSchedule(DefaultSchedule defaultSchedule) {
		getDefaultSchedules().add(defaultSchedule);
		defaultSchedule.setEstablishmentBean(this);

		return defaultSchedule;
	}

	public DefaultSchedule removeDefaultSchedule(DefaultSchedule defaultSchedule) {
		getDefaultSchedules().remove(defaultSchedule);
		defaultSchedule.setEstablishmentBean(null);

		return defaultSchedule;
	}

	public List<EstablishmentEstablishmentType> getEstablishmentEstablishmentTypes() {
		return this.establishmentEstablishmentTypes;
	}

	public void setEstablishmentEstablishmentTypes(List<EstablishmentEstablishmentType> establishmentEstablishmentTypes) {
		this.establishmentEstablishmentTypes = establishmentEstablishmentTypes;
	}

	public EstablishmentEstablishmentType addEstablishmentEstablishmentType(EstablishmentEstablishmentType establishmentEstablishmentType) {
		getEstablishmentEstablishmentTypes().add(establishmentEstablishmentType);
		establishmentEstablishmentType.setEstablishmentBean(this);

		return establishmentEstablishmentType;
	}

	public EstablishmentEstablishmentType removeEstablishmentEstablishmentType(EstablishmentEstablishmentType establishmentEstablishmentType) {
		getEstablishmentEstablishmentTypes().remove(establishmentEstablishmentType);
		establishmentEstablishmentType.setEstablishmentBean(null);

		return establishmentEstablishmentType;
	}

	public List<FeatureEstablishment> getFeatureEstablishments() {
		return this.featureEstablishments;
	}

	public void setFeatureEstablishments(List<FeatureEstablishment> featureEstablishments) {
		this.featureEstablishments = featureEstablishments;
	}

	public FeatureEstablishment addFeatureEstablishment(FeatureEstablishment featureEstablishment) {
		getFeatureEstablishments().add(featureEstablishment);
		featureEstablishment.setEstablishmentBean(this);

		return featureEstablishment;
	}

	public FeatureEstablishment removeFeatureEstablishment(FeatureEstablishment featureEstablishment) {
		getFeatureEstablishments().remove(featureEstablishment);
		featureEstablishment.setEstablishmentBean(null);

		return featureEstablishment;
	}

}