package br.com.ganix.establishment.persistence.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.EstablishmentType;
import br.com.ganix.establishment.persistence.mapper.EstablishmentTypeRowMapper;

public class EstablishmentTypeDAO extends GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7198226922563877740L;

	/**
	 * Create a new Establishment Type
	 * 
	 * @param establishmentType
	 * @return The inserted ID
	 * @throws DataAccessException
	 */
	public long addEstablishmentType(EstablishmentType establishmentType)
			throws DataAccessException {
		Assert.notNull(establishmentType, "Establishment Type is null");
		Assert.notNull(establishmentType.getName(), "Name is null");
		Assert.notNull(establishmentType.getViewerId(),
				"Exhibition Site is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO establishment_type(	");
		sql.append("name, viewer_id )				");
		sql.append("VALUES (?, ?);					");

		Object args[] = { establishmentType.getName(),
				establishmentType.getViewerId() };

		return insertReturnigID(sql.toString(), args).longValue();
	}

	/**
	 * Update an establishment type
	 * @param establishmentType
	 * @return The number of rows affected by the operation
	 * @throws DataAccessException
	 */
	public int updateEstablishmentType(EstablishmentType establishmentType)
			throws DataAccessException {
		Assert.notNull(establishmentType, "Establishment Type is null");
		Assert.notNull(establishmentType.getId(), "ID is null");
		Assert.notNull(establishmentType.getName(), "Name is null");
		Assert.notNull(establishmentType.getViewerId(),
				"Exhibition Site is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE 	establishment_type	");
		sql.append("SET		name = ?, 			");
		sql.append("		viewer_id = ?		");
		sql.append("WHERE 	id = ?				");

		Object args[] = { establishmentType.getName(),
				establishmentType.getViewerId(), establishmentType.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete the specified Establishment Type
	 * @param id
	 * @return The number of rows affected by the operation
	 * @throws DataAccessException
	 */
	public int deleteEstablishmentType(long id)
			throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM establishment_type WHERE id = ?;");

		Object args[] = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * List all Establishment Type from database
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<EstablishmentType> getListEstablishmentTypeMap()
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		name,				");
		sql.append("		viewer_id			");
		sql.append("FROM 	establishment_type	");

		List<EstablishmentType> list = jdbcTemplateReader.query(sql.toString(),
				new EstablishmentTypeRowMapper());
		return list;
	}

	/**
	 * Get the specified Establishment Type
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public EstablishmentType getEstablishmentTypeMap(long id)
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		name,				");
		sql.append("		viewer_id			");
		sql.append("FROM 	establishment_type	");
		sql.append("WHERE 	id = ?				");

		Object args[] = { id };

		List<EstablishmentType> list = jdbcTemplateReader.query(sql.toString(), args,
				new EstablishmentTypeRowMapper());

		return !list.isEmpty() ? list.get(0) : null;
	}
}
