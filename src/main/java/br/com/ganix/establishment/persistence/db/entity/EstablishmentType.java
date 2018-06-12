package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the establishment_type database table.
 * 
 */
@Entity
@Table(name="establishment_type")
@NamedQuery(name="EstablishmentType.findAll", query="SELECT e FROM EstablishmentType e")
public class EstablishmentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTABLISHMENT_TYPE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTABLISHMENT_TYPE_ID_GENERATOR")
	private Integer id;

	private String name;

	@Column(name="viewer_id")
	private String viewerId;

	//bi-directional many-to-one association to EstablishmentEstablishmentType
	@OneToMany(mappedBy="establishmentTypeBean")
	private List<EstablishmentEstablishmentType> establishmentEstablishmentTypes;

	public EstablishmentType() {
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

	public List<EstablishmentEstablishmentType> getEstablishmentEstablishmentTypes() {
		return this.establishmentEstablishmentTypes;
	}

	public void setEstablishmentEstablishmentTypes(List<EstablishmentEstablishmentType> establishmentEstablishmentTypes) {
		this.establishmentEstablishmentTypes = establishmentEstablishmentTypes;
	}

	public EstablishmentEstablishmentType addEstablishmentEstablishmentType(EstablishmentEstablishmentType establishmentEstablishmentType) {
		getEstablishmentEstablishmentTypes().add(establishmentEstablishmentType);
		establishmentEstablishmentType.setEstablishmentTypeBean(this);

		return establishmentEstablishmentType;
	}

	public EstablishmentEstablishmentType removeEstablishmentEstablishmentType(EstablishmentEstablishmentType establishmentEstablishmentType) {
		getEstablishmentEstablishmentTypes().remove(establishmentEstablishmentType);
		establishmentEstablishmentType.setEstablishmentTypeBean(null);

		return establishmentEstablishmentType;
	}

}