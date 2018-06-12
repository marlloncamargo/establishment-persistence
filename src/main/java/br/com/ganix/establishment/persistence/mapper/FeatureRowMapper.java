package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.Feature;

public class FeatureRowMapper implements RowMapper<Feature> {

	@Override
	public Feature mapRow(ResultSet rs, int i) throws SQLException {
		Feature feature = new Feature();
		feature.setId(rs.getLong("id"));
		feature.setName(rs.getString("name"));
		feature.setAbbreviation(rs.getString("abbreviation"));
		feature.setViewerId(rs.getString("viewer_id"));

		return feature;
	}

}
