package br.com.ganix.establishment.persistence.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.Holiday;
import br.com.ganix.establishment.persistence.mapper.HolidayRowMapper;

public class HolidayDAO extends GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7503561147208295801L;

	/**
	 * Create a new holiday and return the ID of the this holiday
	 * 
	 * @param holiday
	 * @return
	 * @throws DataAccessException
	 */
	public long addHoliday(Holiday holiday) throws DataAccessException {
		Assert.notNull(holiday, "Holiday is null");
		Assert.notNull(holiday.getName(), "Name is null");
		Assert.notNull(holiday.getHolidayDate(), "Holiday Date is null");
		Assert.notNull(holiday.getViewerId(), "Exhibition Site is null");

		final StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO holiday(");
		sql.append("name, holiday_date, viewer_id )");
		sql.append("VALUES (?, ?, ?);");

		final Object args[] = { holiday.getName(), holiday.getHolidayDate(),
				holiday.getViewerId() };

		return insertReturnigID(sql.toString(), args).longValue();
	}

	/**
	 * Update a holiday in database
	 * 
	 * @param holiday
	 * @throws DataAccessException
	 */
	public int updateHoliday(Holiday holiday) throws DataAccessException {
		Assert.notNull(holiday, "Holiday is null");
		Assert.notNull(holiday.getId(), "ID is null");
		Assert.notNull(holiday.getName(), "Name is null");
		Assert.notNull(holiday.getHolidayDate(), "Holiday Date is null");
		Assert.notNull(holiday.getViewerId(), "Viewer Id is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE 	holiday				");
		sql.append("SET		name = ?, 			");
		sql.append("		holiday_date = ?, 	");
		sql.append("		viewer_id = ? 		");
		sql.append("WHERE 	id = ? ;			");

		Object args[] = { holiday.getName(), holiday.getHolidayDate(),
				holiday.getViewerId(), holiday.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete from database a holiday
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public int deleteHoliday(long id) throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM	holiday WHERE id = ? ;");

		Object args[] = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * List all existing holidays in the database
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Holiday> getListHolidayMap() throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		name,				");
		sql.append("		holiday_date		");
		sql.append("FROM 	holiday				");

		List<Holiday> list = jdbcTemplateReader.query(sql.toString(),
				new HolidayRowMapper());
		return list;
	}

	/**
	 * Get the specified Holiday from its primary key
	 * 
	 * @param id
	 *            The ID of the holiday
	 * @return
	 * @throws DataAccessException
	 */
	public Holiday getHolidayMap(long id) throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		name,				");
		sql.append("		holiday_date,		");
		sql.append("		viewer_id			");
		sql.append("FROM 	holiday				");
		sql.append("WHERE 	id = ?				");

		Object args[] = { id };

		List<Holiday> list = jdbcTemplateReader.query(sql.toString(), args,
				new HolidayRowMapper());
		return !list.isEmpty() ? list.get(0) : null;
	}
}
