package br.com.ganix.establishment.persistence.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the id_control database table.
 * 
 */
@Entity
@Table(name="id_control")
@NamedQuery(name="IdControl.findAll", query="SELECT i FROM IdControl i")
public class IdControl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ID_CONTROL_TABLENAME_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_CONTROL_TABLENAME_GENERATOR")
	@Column(name="table_name")
	private String tableName;

	@Column(name="next_value")
	private Long nextValue;

	public IdControl() {
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getNextValue() {
		return this.nextValue;
	}

	public void setNextValue(Long nextValue) {
		this.nextValue = nextValue;
	}

}