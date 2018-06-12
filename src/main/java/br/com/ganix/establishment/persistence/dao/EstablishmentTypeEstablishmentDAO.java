package br.com.ganix.establishment.persistence.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.EstablishmentTypeEstablishment;
import br.com.ganix.establishment.persistence.mapper.EstablishmentTypeEstablishmentRowMapper;

public class EstablishmentTypeEstablishmentDAO extends GenericDAO {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5708388782425332966L;

	/**
	 * Create an Establishment Type Establishment
	 * 
	 * @param establishmentTypeEstablishment
	 * @return The ID
	 * @throws DataAccessException
	 */
	public long addEstablishmentTypeEstablishment(
			EstablishmentTypeEstablishment establishmentTypeEstablishment)
			throws DataAccessException {
		Assert.notNull(establishmentTypeEstablishment,
				"Establishment Type Establishment is null");
		Assert.notNull(establishmentTypeEstablishment.getEstablishment(),
				"Establishment is null");
		Assert.notNull(establishmentTypeEstablishment.getEstablishment()
				.getDocumentNumber(), "Establishment Document Number is null");
		Assert.notNull(establishmentTypeEstablishment.getEstablishmentType(),
				"Establishment Type is null");
		Assert.notNull(establishmentTypeEstablishment.getEstablishmentType()
				.getId(), "Establishment Type ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO establishment_establishment_type(	");
		sql.append("establishment, establishment_type )				");
		sql.append("VALUES (?, ?);									");

		Object args[] = {
				establishmentTypeEstablishment.getEstablishment().getId(),
				establishmentTypeEstablishment.getEstablishmentType().getId() };

		return insertReturnigID(sql.toString(), args).longValue();
	}

	/**
	 * Update the specified Establishment Type Establishment
	 * 
	 * @param establishmentTypeEstablishment
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int updateEstablishmentTypeEstablishment(
			EstablishmentTypeEstablishment establishmentTypeEstablishment)
			throws DataAccessException {
		Assert.notNull(establishmentTypeEstablishment,
				"Establishment Type Establishment is null");
		Assert.notNull(establishmentTypeEstablishment.getId(), "ID is null");
		Assert.notNull(establishmentTypeEstablishment.getEstablishment(),
				"Establishment is null");
		Assert.notNull(establishmentTypeEstablishment.getEstablishment()
				.getId(), "Establishment ID is null");
		Assert.notNull(establishmentTypeEstablishment.getEstablishmentType(),
				"Establishment Type is null");
		Assert.notNull(establishmentTypeEstablishment.getEstablishmentType()
				.getId(), "Establishment Type ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE	establishment_establishment_type	");
		sql.append("SET		establishment = ?, 					");
		sql.append("		establishment_type = ? 				");
		sql.append("WHERE 	id = ?;");

		Object args[] = {
				establishmentTypeEstablishment.getEstablishment().getId(),
				establishmentTypeEstablishment.getEstablishmentType().getId(),
				establishmentTypeEstablishment.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete the specified Establishment Type Establishment
	 * @param id
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int deleteEstablishmentTypeEstablishment(long id)
			throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM establishment_establishment_type WHERE id = ?");

		Object args[] = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * List all Establishment Type Establishment from database
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<EstablishmentTypeEstablishment> getEstablishmentTypeEstablishmentMap()
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 								");
		sql.append("		establishment,						");
		sql.append("		establishment_type					");
		sql.append("FROM 	establishment_establishment_type	");

		List<EstablishmentTypeEstablishment> list = jdbcTemplateReader.query(sql.toString(),
				new EstablishmentTypeEstablishmentRowMapper());
		return list;
	}

	/**
	 * List all Establishment Type Establishment based on an Establishment and
	 * on an Establishment Type, if specified
	 * 
	 * @param establishmentId
	 * @param establishmentTypeId
	 * @return
	 * @throws DataAccessException
	 */
	public List<EstablishmentTypeEstablishment> getEstablishmentTypeEstablishmentMap(
			long establishmentId, long establishmentTypeId)
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 								");
		sql.append("		establishment,						");
		sql.append("		establishment_type					");
		sql.append("FROM 	establishment_establishment_type	");
		sql.append("WHERE 	establishment = ?					");

		if (establishmentTypeId > 0)
			sql.append("AND		establishment_type = ?			");

		Object args[] = { establishmentId, establishmentTypeId };

		List<EstablishmentTypeEstablishment> list = jdbcTemplateReader.query(sql.toString(), args,
				new EstablishmentTypeEstablishmentRowMapper());
		return list;
	}
}
