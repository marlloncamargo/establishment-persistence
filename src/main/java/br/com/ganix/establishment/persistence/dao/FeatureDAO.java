package br.com.ganix.establishment.persistence.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.Feature;
import br.com.ganix.establishment.persistence.mapper.FeatureRowMapper;

public class FeatureDAO extends GenericDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1651647273292525309L;

	/**
	 * Create a new feature
	 * 
	 * @param feature
	 * @return The inserted ID
	 * @throws DataAccessException
	 */
	public long addFeature(Feature feature) throws DataAccessException {
		Assert.notNull(feature, "Feature is null");
		Assert.notNull(feature.getName(), "Name is null");
		Assert.notNull(feature.getAbbreviation(), "Abbreviation is null");
		Assert.notNull(feature.getViewerId(), "Exhibition Site is null");
		Assert.notNull(feature.getFeatureGroup(), "Feature Group is null");
		Assert.notNull(feature.getFeatureGroup().getId(),
				"Feature Group ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO feature(");
		sql.append("name, abbreviation, feature_group, viewer_id )");
		sql.append("VALUES (?, ?, ?, ?);");

		Object args[] = { feature.getName(), feature.getAbbreviation(),
				feature.getFeatureGroup().getId(), feature.getViewerId() };

		return insertReturnigID(sql.toString(), args).longValue();
	}

	/**
	 * Update an existing Feature
	 * 
	 * @param feature
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int updateFeature(Feature feature) throws DataAccessException {
		Assert.notNull(feature, "Feature is null");
		Assert.notNull(feature.getId(), "ID is null");
		Assert.notNull(feature.getName(), "Name is null");
		Assert.notNull(feature.getAbbreviation(), "Abbreviation is null");
		Assert.notNull(feature.getViewerId(), "Exhibition Site is null");
		Assert.notNull(feature.getFeatureGroup(), "Feature Group is null");
		Assert.notNull(feature.getFeatureGroup().getId(),
				"Feature Group ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE	feature				");
		sql.append("SET		name = ?, 			");
		sql.append("		abbreviation = ?, 	");
		sql.append("		feature_group = ?, 	");
		sql.append("		viewer_id = ?		");
		sql.append("WHERE 	id = ?;");

		Object args[] = { feature.getName(), feature.getAbbreviation(),
				feature.getFeatureGroup().getId(), feature.getViewerId(),
				feature.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete the specified Feature
	 * 
	 * @param id
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int deleteFeature(long id) throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM feature WHERE id = ?");

		Object args[] = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * List all Features from database
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Feature> getListFeatureMap() throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		name, 				");
		sql.append("		abbreviation, 		");
		sql.append("		feature_group, 		");
		sql.append("		viewer_id	 		");
		sql.append("FROM 	feature				");

		List<Feature> list = jdbcTemplateReader.query(sql.toString(),
				new FeatureRowMapper());
		return list;
	}

	/**
	 * Get the specified feature
	 * 
	 * @param featureGroup
	 * @return
	 * @throws DataAccessException
	 */
	public Feature getFeatureMap(long id) throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		name, 				");
		sql.append("		abbreviation, 		");
		sql.append("		feature_group, 		");
		sql.append("		viewer_id	 		");
		sql.append("FROM 	feature				");
		sql.append("WHERE 	id =	?			");

		List<Feature> list = jdbcTemplateReader.query(sql.toString(),
				new Object[] { id }, new FeatureRowMapper());

		return !list.isEmpty() ? list.get(0) : null;
	}
}
