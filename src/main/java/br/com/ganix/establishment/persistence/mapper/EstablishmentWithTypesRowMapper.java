package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.Establishment;
import br.com.ganix.establishment.persistence.entity.EstablishmentType;
import br.com.ganix.establishment.persistence.entity.EstablishmentTypeEstablishment;

public class EstablishmentWithTypesRowMapper implements
		RowMapper<Establishment> {

	private Establishment establishment = new Establishment();
	
	@Override
	public Establishment mapRow(ResultSet rs, int i) throws SQLException {
		long establishmentID = rs.getLong("id");
		
		if (establishment.getId() != establishmentID) {
			establishment.setId(establishmentID);
			establishment.setDocumentNumber(rs.getLong("document_number"));
			establishment.setDescription(rs.getString("description"));
			establishment.setCompanyName(rs.getString("company_name"));
			establishment.setFantasyName(rs.getString("fantasy_name"));
			establishment.setQuantityLimit(rs.getLong("quantity_limit"));
			establishment.setViewerId(rs.getString("viewer_id"));

			establishment.setEstablishmentTypes(new ArrayList<EstablishmentTypeEstablishment>());
		}
		
		EstablishmentTypeEstablishment establishmentTypeEstablishment = new EstablishmentTypeEstablishment();
		establishmentTypeEstablishment.setEstablishment(establishment);
		establishmentTypeEstablishment.setId(rs.getLong("establishment_establishment_type_id"));

		EstablishmentType establishmentType = new EstablishmentType();
		establishmentType.setId(rs.getLong("establishment_type_id"));
		establishmentType.setName(rs.getString("establishment_type_name"));
		establishmentType.setViewerId(rs
				.getString("establishment_type_viewer_id"));

		establishmentTypeEstablishment.setEstablishmentType(establishmentType);
		establishment.getEstablishmentTypes().add(
				establishmentTypeEstablishment);

		return establishment;
	}
}
