package br.com.ganix.establishment.persistence.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.AlternateSchedule;
import br.com.ganix.establishment.persistence.mapper.AlternateScheduleRowMapper;

public class AlternateScheduleDAO extends GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8576790616102082396L;

	/**
	 * Create an Alternate Schedule
	 * 
	 * @param alternateSchedule
	 * @throws DataAccessException
	 */
	public long addAlternateSchedule(AlternateSchedule alternateSchedule)
			throws DataAccessException {
		Assert.notNull(alternateSchedule, "Alternate Schedule is null");
		Assert.notNull(alternateSchedule.getDistinctDay(),
				"Distinct Day is null");
		Assert.notNull(alternateSchedule.getOpeningTime(),
				"Opening Time is null");
		Assert.notNull(alternateSchedule.getClosingTime(),
				"Closing Time is null");
		Assert.notNull(alternateSchedule.getEstablishment(),
				"Establishment is null");
		Assert.notNull(alternateSchedule.getEstablishment().getId(),
				"Establishment ID is null");
		Assert.notNull(alternateSchedule.getViewerId(),
				"Viewer ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO alternate_schedule(				");
		sql.append("distinct_day, opening_time, closing_time, 	");
		sql.append("establishment, holiday, viewer_id )			");
		sql.append("VALUES (?, ?, ?, ?, ?, ?);					");

		Object args[] = {
				alternateSchedule.getDistinctDay(),
				alternateSchedule.getOpeningTime(),
				alternateSchedule.getClosingTime(),
				alternateSchedule.getEstablishment().getId(),
				alternateSchedule.getHoliday() != null ? alternateSchedule
						.getHoliday().getId() : null,
				alternateSchedule.getViewerId() };

//		jdbcTemplate.update(sql.toString(), args);
		return insertReturnigID(sql.toString(), args).longValue();
	}

	/**
	 * Update an existing Alternate Schedule
	 * 
	 * @param alternateSchedule
	 * @return number of rows affected
	 * @throws DataAccessException
	 */
	public int updateAlternateSchedule(AlternateSchedule alternateSchedule)
			throws DataAccessException {
		Assert.notNull(alternateSchedule, "Alternate Schedule is null");
		Assert.notNull(alternateSchedule.getId(), "ID is null");
		Assert.notNull(alternateSchedule.getDistinctDay(),
				"Distinct Day is null");
		Assert.notNull(alternateSchedule.getOpeningTime(),
				"Opening Time is null");
		Assert.notNull(alternateSchedule.getClosingTime(),
				"Closing Time is null");
		Assert.notNull(alternateSchedule.getEstablishment(),
				"Establishment is null");
		Assert.notNull(alternateSchedule.getEstablishment().getId(),
				"Establishment ID is null");
		Assert.notNull(alternateSchedule.getViewerId(),
				"Viewer ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE 	alternate_schedule	");
		sql.append("SET		distinct_day = ?, 	");
		sql.append("		opening_time = ?, 	");
		sql.append("		closing_time = ?, 	");
		sql.append("		establishment = ?, 	");
		sql.append("		holiday = ?,		");
		sql.append(" 		viewer_id = ? 		");
		sql.append("WHERE 	id = ?;				");

		Object args[] = {
				alternateSchedule.getDistinctDay(),
				alternateSchedule.getOpeningTime(),
				alternateSchedule.getClosingTime(),
				alternateSchedule.getEstablishment().getId(),
				alternateSchedule.getHoliday() != null ? alternateSchedule
						.getHoliday().getId() : null,
				alternateSchedule.getViewerId(), alternateSchedule.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete an specified alternate schedule from database
	 * 
	 * @param id
	 * @return Number of rows deleted
	 * @throws DataAccessException
	 */
	public int deleteAlternateSchedule(long id) throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM alternate_schedule WHERE id = ?;");

		Object args[] = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * List all Alternate Schedule from database
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<AlternateSchedule> getListAlternateScheduleMap()
			throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		distinct_day,		");
		sql.append("		opening_time,		");
		sql.append("		closing_time, 		");
		sql.append("		establishment, 		");
		sql.append("		viewer_id, 			");
		sql.append("		holiday 			");
		sql.append("FROM 	alternate_schedule	");

		List<AlternateSchedule> list = jdbcTemplateReader.query(sql.toString(),
				new AlternateScheduleRowMapper());
		return list;
	}

	/**
	 * Get all Alternate Schedules of an Establishment from database
	 * 
	 * @param establishmentId
	 * @return
	 * @throws DataAccessException
	 */
	public List<AlternateSchedule> getListAlternateScheduleEstablishment(
			long establishmentId) throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		distinct_day,		");
		sql.append("		opening_time,		");
		sql.append("		closing_time, 		");
		sql.append("		establishment, 		");
		sql.append("		viewer_id, 			");
		sql.append("		holiday 			");
		sql.append("FROM 	alternate_schedule	");
		sql.append("WHERE 	establishment =	?	");

		List<AlternateSchedule> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { establishmentId },
				new AlternateScheduleRowMapper());
		return list;
	}

	/**
	 * Get all Alternate Schedules of an Establishment specific date from database
	 * 
	 * @param establishmentId
	 * @param date
	 * @return
	 * @throws DataAccessException
	 */
	public AlternateSchedule getAlternateScheduleEstablishmentAndDate(
			long establishmentId, Date date) throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		distinct_day,		");
		sql.append("		opening_time,		");
		sql.append("		closing_time, 		");
		sql.append("		establishment, 		");
		sql.append("		viewer_id, 			");
		sql.append("		holiday 			");
		sql.append("FROM 	default_schedule	");
		sql.append("WHERE 	establishment =	?	");
		sql.append("AND 	distinct_day =	?	");
		
		List<AlternateSchedule> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { establishmentId, date },
				new AlternateScheduleRowMapper());
		return !list.isEmpty() ? list.get(0) : null;
	}
	
	/**
	 * Get an existing Alternate Schedule
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public AlternateSchedule getAlternateSchedule(long id)
			throws DataAccessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		distinct_day,		");
		sql.append("		opening_time,		");
		sql.append("		closing_time, 		");
		sql.append("		establishment, 		");
		sql.append("		viewer_id, 			");
		sql.append("		holiday 			");
		sql.append("FROM 	alternate_schedule	");
		sql.append("WHERE 	id  =	?	");

		List<AlternateSchedule> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { id }, new AlternateScheduleRowMapper());
		return !list.isEmpty() ? list.get(0) : null;
	}
}
