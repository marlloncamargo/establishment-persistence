package br.com.ganix.establishment.persistence.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.DefaultSchedule;
import br.com.ganix.establishment.persistence.mapper.DefaultScheduleRowMapper;

public class DefaultScheduleDAO extends GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5890176680707340243L;

	/**
	 * Create a Default Schedule
	 * 
	 * @param defaultSchedule
	 * @return the inserted id
	 * @throws DataAccessException
	 */
	public long addDefaultSchedule(DefaultSchedule defaultSchedule)
			throws DataAccessException {
		Assert.notNull(defaultSchedule, "Default Schedule is null");
		Assert.notNull(defaultSchedule.getOpeningTime(), "Opening Time is null");
		Assert.notNull(defaultSchedule.getClosingTime(), "Closing Time is null");
		Assert.notNull(defaultSchedule.getDayOfWeek(), "Day of Week is null");
		Assert.notNull(defaultSchedule.getEstablishment(),
				"Establishment is null");
		Assert.notNull(defaultSchedule.getEstablishment().getId(),
				"Establishment ID is null");
		Assert.notNull(defaultSchedule.getViewerId(), "Exhibition Site is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO default_schedule(				");
		sql.append("opening_time, closing_time, day_of_week, 	");
		sql.append("establishment, viewer_id)					");
		sql.append("VALUES (?, ?, ?, ?, ?);						");

		Object args[] = { defaultSchedule.getOpeningTime(),
				defaultSchedule.getClosingTime(),
				defaultSchedule.getDayOfWeek(),
				defaultSchedule.getEstablishment().getId(),
				defaultSchedule.getViewerId() };

		return insertReturnigID(sql.toString(), args).longValue();
	}

	/**
	 * Update a Default Schedule
	 * 
	 * @param defaultSchedule
	 * @return The number of rows affected by the update
	 * @throws DataAccessException
	 */
	public int updateDefaultSchedule(DefaultSchedule defaultSchedule)
			throws DataAccessException {
		Assert.notNull(defaultSchedule, "Default Schedule is null");
		Assert.notNull(defaultSchedule.getId(), "ID is null");
		Assert.notNull(defaultSchedule.getOpeningTime(), "Opening Time is null");
		Assert.notNull(defaultSchedule.getClosingTime(), "Closing Time is null");
		Assert.notNull(defaultSchedule.getDayOfWeek(), "Day of Week is null");
		Assert.notNull(defaultSchedule.getEstablishment(),
				"Establishment is null");
		Assert.notNull(defaultSchedule.getEstablishment().getId(),
				"Establishment ID is null");
		Assert.notNull(defaultSchedule.getViewerId(), "Exhibition Site is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE 	default_schedule	");
		sql.append("SET		opening_time = ?, 	");
		sql.append("		closing_time = ?, 	");
		sql.append("		day_of_week = ?, 	");
		sql.append("		establishment = ?, 	");
		sql.append("		viewer_id = ?		");
		sql.append("WHERE 	id = ?;				");

		Object args[] = { defaultSchedule.getOpeningTime(),
				defaultSchedule.getClosingTime(),
				defaultSchedule.getDayOfWeek(),
				defaultSchedule.getEstablishment().getId(),
				defaultSchedule.getViewerId(), defaultSchedule.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete the specified Default Schedule
	 * 
	 * @param id
	 * @return The number of rows affected by the operation
	 * @throws DataAccessException
	 */
	public int deleteDefaultSchedule(long id) throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM	default_schedule WHERE id = ?;");

		Object args[] = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * List all Default Schedule from database
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<DefaultSchedule> getListDefaultScheduleMap()
			throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		day_of_week,		");
		sql.append("		opening_time,		");
		sql.append("		closing_time, 		");
		sql.append("		establishment, 		");
		sql.append("		viewer_id 			");
		sql.append("FROM 	default_schedule	");

		List<DefaultSchedule> list = jdbcTemplateReader.query(sql.toString(),
				new DefaultScheduleRowMapper());
		return list;
	}

	/**
	 * List all Default Schedule of an Establishment from database
	 * 
	 * @param establishmentId
	 * @return
	 * @throws DataAccessException
	 */
	public List<DefaultSchedule> getListDefaultScheduleEstablishment(
			long establishmentId) throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		day_of_week,		");
		sql.append("		opening_time,		");
		sql.append("		closing_time, 		");
		sql.append("		establishment, 		");
		sql.append("		viewer_id 			");
		sql.append("FROM 	default_schedule	");
		sql.append("WHERE 	establishment =	?	");

		List<DefaultSchedule> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { establishmentId },
				new DefaultScheduleRowMapper());
		return list;
	}

	/**
	 * Get the specified Default Schedule
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public DefaultSchedule getDefaultScheduleMap(long id)
			throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		day_of_week,		");
		sql.append("		opening_time,		");
		sql.append("		closing_time, 		");
		sql.append("		establishment, 		");
		sql.append("		viewer_id 			");
		sql.append("FROM 	default_schedule	");
		sql.append("WHERE 	id =	?			");

		List<DefaultSchedule> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { id }, new DefaultScheduleRowMapper());
		return !list.isEmpty() ? list.get(0) : null;
	}
	
	/**
	 * Get all Alternate Schedules of an Establishment from database
	 * 
	 * @param establishmentId
	 * @return
	 * @throws DataAccessException
	 */
	public DefaultSchedule getDefaultScheduleEstablishmentAndDayOfWeek(
			long establishmentId, String dayOfWeek) throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		day_of_week,		");
		sql.append("		opening_time,		");
		sql.append("		closing_time, 		");
		sql.append("		establishment, 		");
		sql.append("		viewer_id 			");
		sql.append("FROM 	default_schedule	");
		sql.append("WHERE 	establishment =	?	");
		sql.append("AND 	day_of_week =	?	");
		
		List<DefaultSchedule> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { establishmentId, dayOfWeek },
				new DefaultScheduleRowMapper());
		return !list.isEmpty() ? list.get(0) : null;
	}
}
