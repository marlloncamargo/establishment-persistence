package br.com.ganix.establishment.persistence.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.Seasonality;
import br.com.ganix.establishment.persistence.mapper.SeasonalityRowMapper;

public class SeasonalityDAO extends GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2704667014743484852L;

	public Seasonality getSeasonality(long establishmentId) throws DataAccessException {
		Assert.notNull(establishmentId, "Establishment ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT establishment, days, registry_time	");
		sql.append("FROM seasonality							");
		sql.append("WHERE establishment = ?						");

		Object args[] = { establishmentId };
		
		List<Seasonality> list = jdbcTemplateReader.query(sql.toString(), args, new SeasonalityRowMapper());
		
		return !list.isEmpty() ? list.get(0) : null; 
	}

	public int addSeasonality(Seasonality seasonality) throws DataAccessException {
		Assert.notNull(seasonality, "Seasonality is null");
		Assert.notNull(seasonality.getEstablishment(), "Establishment is null");
		Assert.notNull(seasonality.getEstablishment().getId(), "Establishment ID is null");
		Assert.notNull(seasonality.getDays(), "Time in days is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO seasonality 			");
		sql.append("establishment, days, registry_time)	");
		sql.append("VALUES (?, ?, ?)					");

		Timestamp now = new Timestamp(new Date().getTime());
		Object args[] = { seasonality.getEstablishment().getId(), seasonality.getDays(), now };
		return jdbcTemplate.update(sql.toString(), args);
	}
}
