package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the feature_group database table.
 * 
 */
@Entity
@Table(name="feature_group")
@NamedQuery(name="FeatureGroup.findAll", query="SELECT f FROM FeatureGroup f")
public class FeatureGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FEATURE_GROUP_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FEATURE_GROUP_ID_GENERATOR")
	private Integer id;

	private String name;

	@Column(name="viewer_id")
	private String viewerId;

	//bi-directional many-to-one association to Feature
	@OneToMany(mappedBy="featureGroupBean")
	private List<Feature> features;

	public FeatureGroup() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Feature> getFeatures() {
		return this.features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public Feature addFeature(Feature feature) {
		getFeatures().add(feature);
		feature.setFeatureGroupBean(this);

		return feature;
	}

	public Feature removeFeature(Feature feature) {
		getFeatures().remove(feature);
		feature.setFeatureGroupBean(null);

		return feature;
	}

}