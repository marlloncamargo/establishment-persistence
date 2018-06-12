package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.Establishment;

public class EstablishmentRowMapper implements RowMapper<Establishment> {

	@Override
	public Establishment mapRow(ResultSet rs, int i) throws SQLException {
		
		/*
		 * 
		sql.append("   		establishment_type, ");

		 */
		
		Establishment es = new Establishment();
		es.setId(rs.getLong("id"));
		es.setDocumentNumber(rs.getLong("document_number"));
		es.setCompanyName(rs.getString("company_name"));
		es.setFantasyName(rs.getString("fantasy_name"));
		es.setQuantityLimit(rs.getLong("quantity_limit"));
		es.setDescription(rs.getString("description"));
		es.setViewerId(rs.getString("viewer_id"));
		
		return es;
	}
}
