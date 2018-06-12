package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.FeatureGroup;

public class FeatureGroupRowMapper implements RowMapper<FeatureGroup> {

	@Override
	public FeatureGroup mapRow(ResultSet rs, int i) throws SQLException {
		FeatureGroup featureGroup = new FeatureGroup();
		featureGroup.setId(rs.getLong("id"));
		featureGroup.setName(rs.getString("name"));
		featureGroup.setViewerId(rs.getString("viewer_id"));

		return featureGroup;
	}

}
