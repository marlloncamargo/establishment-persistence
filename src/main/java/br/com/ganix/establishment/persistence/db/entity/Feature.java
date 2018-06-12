package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the feature database table.
 * 
 */
@Entity
@NamedQuery(name="Feature.findAll", query="SELECT f FROM Feature f")
public class Feature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FEATURE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FEATURE_ID_GENERATOR")
	private Integer id;

	private String abbreviation;

	private String name;

	@Column(name="viewer_id")
	private String viewerId;

	//bi-directional many-to-one association to FeatureGroup
	@ManyToOne
	@JoinColumn(name="feature_group")
	private FeatureGroup featureGroupBean;

	//bi-directional many-to-one association to FeatureEstablishment
	@OneToMany(mappedBy="featureBean")
	private List<FeatureEstablishment> featureEstablishments;

	public Feature() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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

	public FeatureGroup getFeatureGroupBean() {
		return this.featureGroupBean;
	}

	public void setFeatureGroupBean(FeatureGroup featureGroupBean) {
		this.featureGroupBean = featureGroupBean;
	}

	public List<FeatureEstablishment> getFeatureEstablishments() {
		return this.featureEstablishments;
	}

	public void setFeatureEstablishments(List<FeatureEstablishment> featureEstablishments) {
		this.featureEstablishments = featureEstablishments;
	}

	public FeatureEstablishment addFeatureEstablishment(FeatureEstablishment featureEstablishment) {
		getFeatureEstablishments().add(featureEstablishment);
		featureEstablishment.setFeatureBean(this);

		return featureEstablishment;
	}

	public FeatureEstablishment removeFeatureEstablishment(FeatureEstablishment featureEstablishment) {
		getFeatureEstablishments().remove(featureEstablishment);
		featureEstablishment.setFeatureBean(null);

		return featureEstablishment;
	}

}