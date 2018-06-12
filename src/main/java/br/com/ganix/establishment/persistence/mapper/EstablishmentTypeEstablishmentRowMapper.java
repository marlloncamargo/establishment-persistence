package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.Establishment;
import br.com.ganix.establishment.persistence.entity.EstablishmentType;
import br.com.ganix.establishment.persistence.entity.EstablishmentTypeEstablishment;

public class EstablishmentTypeEstablishmentRowMapper implements
		RowMapper<EstablishmentTypeEstablishment> {

	@Override
	public EstablishmentTypeEstablishment mapRow(ResultSet rs, int i)
			throws SQLException {
		EstablishmentTypeEstablishment establishmentTypeEstablishment = new EstablishmentTypeEstablishment();
		establishmentTypeEstablishment.setId(rs.getLong("id"));

		Establishment establishment = new Establishment();
		establishment.setId(rs.getLong("establishment"));
		establishmentTypeEstablishment.setEstablishment(establishment);

		EstablishmentType establishmentType = new EstablishmentType();
		establishmentType.setId(rs.getLong("establishment_type"));
		establishmentTypeEstablishment.setEstablishmentType(establishmentType);

		return establishmentTypeEstablishment;
	}

}
