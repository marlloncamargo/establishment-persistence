package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.FeatureEstablishment;

public class FeatureEstablishmentRowMapper implements
		RowMapper<FeatureEstablishment> {

	@Override
	public FeatureEstablishment mapRow(ResultSet rs, int i) throws SQLException {
		FeatureEstablishment featureEstablishment = new FeatureEstablishment();
		featureEstablishment.setId(rs.getLong("id"));
		featureEstablishment.setValue(rs.getString("value"));

		return featureEstablishment;
	}

}
