package br.com.ganix.establishment.persistence.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.FeatureGroup;
import br.com.ganix.establishment.persistence.mapper.FeatureGroupRowMapper;

public class FeatureGroupDAO extends GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6576046421049628010L;

	/**
	 * Create a new Feature Group on database
	 * 
	 * @param featureGroup
	 * @return The ID
	 * @throws DataAccessException
	 */
	public long addFeatureGroup(FeatureGroup featureGroup)
			throws DataAccessException {
		Assert.notNull(featureGroup, "Feature Group is null");
		Assert.notNull(featureGroup.getName(), "Name is null");
		Assert.notNull(featureGroup.getViewerId(), "Exhibition Site is null");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO feature_group(	");
		sql.append("name, viewer_id )			");
		sql.append("VALUES (?, ?);				");

		Object args[] = { featureGroup.getName(), featureGroup.getViewerId() };
		
		return insertReturnigID(sql.toString(), args).longValue();
	}

	/**
	 * Update a Feature Group on database0
	 * 
	 * @param featureGroup
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int updateFeatureGroup(FeatureGroup featureGroup)
			throws DataAccessException {
		Assert.notNull(featureGroup, "Feature Group is null");
		Assert.notNull(featureGroup.getId(), "ID is null");
		Assert.notNull(featureGroup.getName(), "Name is null");
		Assert.notNull(featureGroup.getViewerId(), "Exhibition Site is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE 	feature_group	");
		sql.append("SET 	name = ?, 		");
		sql.append("		viewer_id = ?	");
		sql.append("WHERE 	id = ?			");

		Object args[] = { featureGroup.getName(), featureGroup.getViewerId(),
				featureGroup.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete a Feature Group
	 * 
	 * @param id
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int deleteFeatureGroup(long id) throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM feature_group WHERE id = ?");

		Object args[] = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Get the specified Feature Group
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public FeatureGroup getFeatureGroupMap(long id) throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		name,	 			");
		sql.append("		viewer_id 			");
		sql.append("FROM 	feature_group		");
		sql.append("WHERE 	id = ?				");

		Object args[] = { id };

		List<FeatureGroup> list = jdbcTemplateReader.query(sql.toString(), args,
				new FeatureGroupRowMapper());

		return !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * List all Feature Group from database
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<FeatureGroup> getListFeatureGroupMap()
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		name,	 			");
		sql.append("		viewer_id 			");
		sql.append("FROM 	feature_group		");

		List<FeatureGroup> list = jdbcTemplateReader.query(sql.toString(),
				new FeatureGroupRowMapper());
		return list;
	}
}
