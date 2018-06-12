package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the feature_establishment database table.
 * 
 */
@Entity
@Table(name="feature_establishment")
@NamedQuery(name="FeatureEstablishment.findAll", query="SELECT f FROM FeatureEstablishment f")
public class FeatureEstablishment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FEATURE_ESTABLISHMENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FEATURE_ESTABLISHMENT_ID_GENERATOR")
	private Integer id;

	private String value;

	//bi-directional many-to-one association to Establishment
	@ManyToOne
	@JoinColumn(name="establishment")
	private Establishment establishmentBean;

	//bi-directional many-to-one association to Feature
	@ManyToOne
	@JoinColumn(name="feature")
	private Feature featureBean;

	public FeatureEstablishment() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Establishment getEstablishmentBean() {
		return this.establishmentBean;
	}

	public void setEstablishmentBean(Establishment establishmentBean) {
		this.establishmentBean = establishmentBean;
	}

	public Feature getFeatureBean() {
		return this.featureBean;
	}

	public void setFeatureBean(Feature featureBean) {
		this.featureBean = featureBean;
	}

}