package br.com.ganix.establishment.persistence.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.ControlID;
import br.com.ganix.establishment.persistence.exceptions.GanixEstablishmentPersistenceException;
import br.com.ganix.establishment.persistence.mapper.ControlIDRowMapper;

public class ControlIDDAO extends GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5186176455944019767L;

	/**
	 * Get the next id from the specified table and seeds the sequence
	 * 
	 * @param tableName
	 *            The name of the table to seek the next id
	 * @return The next id
	 * @throws DataAccessException
	 * @throws GanixEstablishmentPersistenceException
	 *             No next ID is found for the table. Check the database to see
	 *             if there's a row with the specified table
	 */
	@Transactional(rollbackFor = GanixEstablishmentPersistenceException.class, rollbackForClassName = "DataAccessException")
	public long getNextValue(String tableName) throws DataAccessException,
			GanixEstablishmentPersistenceException {
		Assert.notNull(tableName, "Table Name is null");

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	next_value,					");
		sql.append("		table_name					");
		sql.append("FROM 	id_control					");
		sql.append("WHERE 	table_name = ?;				");

		Object args[] = { tableName };
		List<ControlID> list = jdbcTemplateReader.query(sql.toString(), args,
				new ControlIDRowMapper());
		
		sql = new StringBuffer();
		sql.append("UPDATE id_control					");
		sql.append("SET 	next_value = next_value + 1	");
		sql.append("WHERE 	table_name = ?				");
		int result = jdbcTemplate.update(sql.toString(), args);

		if (result == 0)
			throw new GanixEstablishmentPersistenceException(
					"Failed to update next value of primary key for table "
							+ tableName + ".");

		if (list.isEmpty())
			throw new GanixEstablishmentPersistenceException(
					"Failed to get next value of primary key for table "
							+ tableName + ". The returning list is empty.");

		return list.get(0).getNextValue();
	}

	/**
	 * Create a new Control ID
	 * 
	 * @param controlID
	 * @return The number of rows affected
	 */
	public int addControlID(ControlID controlID) {
		Assert.notNull(controlID, "Control ID is null");
		Assert.notNull(controlID.getTableName(), "Table Name is null");
		Assert.notNull(controlID.getNextValue(), "Next Value is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO	id_control (	");
		sql.append("table_name, next_value)		");
		sql.append("VALUES (?, ?)				");

		Object args[] = { controlID.getTableName(), controlID.getNextValue() };
		return jdbcTemplate.update(sql.toString(), args);

	}
	
	/**
	 * Delete a Control ID
	 * @param tableName
	 * @return The number of rows affected
	 */
	public int deleteControlID(String tableName) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM id_control WHERE table_name = ?");
		Object args[] = { tableName };
		return jdbcTemplate.update(sql.toString(), args);
	}
}
