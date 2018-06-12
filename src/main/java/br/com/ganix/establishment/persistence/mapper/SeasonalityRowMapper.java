package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.Establishment;
import br.com.ganix.establishment.persistence.entity.Seasonality;

public class SeasonalityRowMapper implements RowMapper<Seasonality> {

	@Override
	public Seasonality mapRow(ResultSet rs, int i) throws SQLException {
		Seasonality seasonality = new Seasonality();
		Establishment establishment = new Establishment();
		establishment.setId(rs.getLong("establishment"));
		seasonality.setEstablishment(establishment);
		seasonality.setDays(rs.getInt("days"));
		seasonality.setRegistryTime(rs.getTimestamp("registry_time"));
		
		return seasonality;
	}

}
