package br.com.ganix.establishment.persistence.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.FeatureEstablishment;
import br.com.ganix.establishment.persistence.mapper.FeatureEstablishmentRowMapper;

public class FeatureEstablishmentDAO extends GenericDAO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 365540687885302912L;

	/**
	 * Add a new Feature Establishment on database
	 * 
	 * @param featureEstablishment
	 *            The object to be inserted. Establishment and Feature objects
	 *            must be instantiated
	 * @return The ID
	 * @throws DataAccessException
	 */
	public long addFeatureEstablishment(
			FeatureEstablishment featureEstablishment)
			throws DataAccessException {
		Assert.notNull(featureEstablishment, "Feature Establishment is null");
		Assert.notNull(featureEstablishment.getValue(), "Value is null");
		Assert.notNull(featureEstablishment.getEstablishment(),
				"Establishment is null");
		Assert.notNull(featureEstablishment.getEstablishment().getId(),
				"Establishment CNPJ is null");
		Assert.notNull(featureEstablishment.getFeature(), "Feature is null");
		Assert.notNull(featureEstablishment.getFeature().getId(),
				"Feature ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO feature_establishment (	");
		sql.append("value, establishment, feature )		");
		sql.append("VALUES (?, ?, ?);					");

		Object args[] = { featureEstablishment.getValue(),
				featureEstablishment.getEstablishment().getId(),
				featureEstablishment.getFeature().getId() };

		return insertReturnigID(sql.toString(), args).longValue();
	}

	/**
	 * Update a Feature Establishment
	 * 
	 * @param featureEstablishment
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int updateFeatureEstablishment(
			FeatureEstablishment featureEstablishment)
			throws DataAccessException {
		Assert.notNull(featureEstablishment, "Feature Establishment is null");
		Assert.notNull(featureEstablishment.getId(), "ID is null");
		Assert.notNull(featureEstablishment.getValue(), "Value is null");
		Assert.notNull(featureEstablishment.getEstablishment(),
				"Establishment is null");
		Assert.notNull(featureEstablishment.getEstablishment().getId(),
				"Establishment CNPJ is null");
		Assert.notNull(featureEstablishment.getFeature(), "Feature is null");
		Assert.notNull(featureEstablishment.getFeature().getId(),
				"Feature ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE	feature_establishment	");
		sql.append("SET		value = ?,  			");
		sql.append("		establishment = ?,		");
		sql.append("		feature = ?				");
		sql.append("WHERE 	id = ? 					");

		Object args[] = { featureEstablishment.getValue(),
				featureEstablishment.getEstablishment().getId(),
				featureEstablishment.getFeature().getId(),
				featureEstablishment.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete a Feature Establishment from database
	 * @param id
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int deleteFeatureEstablishment(long id) throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM	feature_establishment WHERE id = ?	");

		Object args[] = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Get the specified Feature Establishment from database
	 * 
	 * @param establishmentId
	 * @param featureId
	 * @return
	 * @throws DataAccessException
	 */
	public FeatureEstablishment getFeatureEstablishmentMap(long id)
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 					");
		sql.append("		value, 					");
		sql.append("		establishment,			");
		sql.append("		feature					");
		sql.append("FROM 	feature_establishment	");
		sql.append("WHERE 	id = ?					");

		List<FeatureEstablishment> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { id }, new FeatureEstablishmentRowMapper());

		return !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * List all Feature Establishment from database based on a Establishment
	 * 
	 * @param establishmentId
	 * @return
	 * @throws DataAccessException
	 */
	public List<FeatureEstablishment> getListFeatureEstablishmentMap(
			long establishmentId) throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 					");
		sql.append("		value, 					");
		sql.append("		establishment,			");
		sql.append("		feature					");
		sql.append("FROM 	feature_establishment	");
		sql.append("WHERE 	establishment = ?		");

		List<FeatureEstablishment> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { establishmentId },
				new FeatureEstablishmentRowMapper());
		return list;
	}
}
