package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.EstablishmentType;

public class EstablishmentTypeRowMapper implements RowMapper<EstablishmentType> {

	@Override
	public EstablishmentType mapRow(ResultSet rs, int i) throws SQLException {
		EstablishmentType establishmentType = new EstablishmentType();
		establishmentType.setId(rs.getLong("id"));
		establishmentType.setName(rs.getString("name"));
		establishmentType.setViewerId(rs.getString("viewer_id"));

		return establishmentType;
	}

}
