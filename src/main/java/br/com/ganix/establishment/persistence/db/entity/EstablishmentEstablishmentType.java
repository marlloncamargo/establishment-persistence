package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the establishment_establishment_type database table.
 * 
 */
@Entity
@Table(name="establishment_establishment_type")
@NamedQuery(name="EstablishmentEstablishmentType.findAll", query="SELECT e FROM EstablishmentEstablishmentType e")
public class EstablishmentEstablishmentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESTABLISHMENT_ESTABLISHMENT_TYPE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTABLISHMENT_ESTABLISHMENT_TYPE_ID_GENERATOR")
	private Integer id;

	//bi-directional many-to-one association to Establishment
	@ManyToOne
	@JoinColumn(name="establishment")
	private Establishment establishmentBean;

	//bi-directional many-to-one association to EstablishmentType
	@ManyToOne
	@JoinColumn(name="establishment_type")
	private EstablishmentType establishmentTypeBean;

	public EstablishmentEstablishmentType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Establishment getEstablishmentBean() {
		return this.establishmentBean;
	}

	public void setEstablishmentBean(Establishment establishmentBean) {
		this.establishmentBean = establishmentBean;
	}

	public EstablishmentType getEstablishmentTypeBean() {
		return this.establishmentTypeBean;
	}

	public void setEstablishmentTypeBean(EstablishmentType establishmentTypeBean) {
		this.establishmentTypeBean = establishmentTypeBean;
	}

}