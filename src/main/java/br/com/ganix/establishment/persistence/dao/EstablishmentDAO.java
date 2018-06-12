package br.com.ganix.establishment.persistence.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.ganix.establishment.persistence.entity.Establishment;
import br.com.ganix.establishment.persistence.entity.EstablishmentTypeEstablishment;
import br.com.ganix.establishment.persistence.exceptions.GanixEstablishmentPersistenceException;
import br.com.ganix.establishment.persistence.mapper.EstablishmentRowMapper;
import br.com.ganix.establishment.persistence.mapper.EstablishmentWithTypesRowMapper;
import br.com.ganix.establishment.persistence.repository.EstablishmentRepository;

public class EstablishmentDAO extends GenericDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3102349680462225926L;
	
	@Autowired
	@Qualifier("controlIDDAO")
	private ControlIDDAO controlIDDAO;
	
	@Autowired
	@Qualifier("establishmentTypeEstablishmentDAO")
	private EstablishmentTypeEstablishmentDAO establishmentTypeEstablishmentDAO;

	private EstablishmentRepository repository;
	
	/**
	 * Add a new establishment entity into the database.
	 * 
	 * @param establishment
	 *            - The entity to be inserted
	 * @return The ID of the inserted establishment
	 * @throws DataAccessException
	 * @throws GanixEstablishmentPersistenceException
	 */
	@Transactional
	public long addEstablishment(Establishment establishment)
			throws DataAccessException, GanixEstablishmentPersistenceException {
		long establishmentID = 0;

		Assert.notNull(establishment, "establishment is null");
		Assert.notNull(establishment.getDocumentNumber(), "document number is null");
		Assert.notNull(establishment.getCompanyName(), "Company Name is null");
		Assert.notNull(establishment.getFantasyName(), "Fantasy Name is null");
		Assert.notNull(establishment.getQuantityLimit(),
				"Quantity Limit is null");
		Assert.notNull(establishment.getViewerId(), "Exhibition Site is null");
		Assert.notNull(establishment.getDescription(), "Establishment description is null");

		establishmentID = controlIDDAO.getNextValue("establishment");

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO establishment(						");
		sql.append("id, document_number, company_name, fantasy_name,			");
		sql.append("quantity_limit,	");
		sql.append("description ");
		sql.append("viewer_id )										");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?);		");

		Object[] args = { establishmentID, establishment.getDocumentNumber(),
				establishment.getCompanyName(), establishment.getFantasyName(),
				establishment.getQuantityLimit(),
				establishment.getDescription(), establishment.getViewerId() };

		jdbcTemplate.update(sql.toString(), args);

		return establishmentID;
	}
	
	@Transactional
	public long addEstablishmentWithTypes(Establishment establishment) throws DataAccessException, GanixEstablishmentPersistenceException{
		Assert.notNull(establishment.getEstablishmentTypes());
		Assert.notEmpty(establishment.getEstablishmentTypes());
		
		long establishmentID = addEstablishment(establishment);
		establishment.setId(establishmentID);
		for(EstablishmentTypeEstablishment establishmentTypeEstablishment : establishment.getEstablishmentTypes()){
			establishmentTypeEstablishmentDAO.addEstablishmentTypeEstablishment(establishmentTypeEstablishment);
		}
		
		return establishmentID;
	}

	/**
	 * Update an existing Establishment
	 * 
	 * @param establishment
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int updateEstablishment(Establishment establishment)
			throws DataAccessException {

		Assert.notNull(establishment, "establishment is null");
		Assert.notNull(establishment.getDocumentNumber(), "document number is null");
		Assert.notNull(establishment.getCompanyName(), "Company Name is null");
		Assert.notNull(establishment.getFantasyName(), "Fantasy Name is null");
		Assert.notNull(establishment.getQuantityLimit(),
				"Quantity Limit is null");
		Assert.notNull(establishment.getViewerId(), "Exhibition Site is null");
		Assert.notNull(establishment.getDescription(), "Establishment description is null");

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE 	establishment			");
		sql.append("SET		cnpj = ?, 				");
		sql.append("		company_name = ?, 		");
		sql.append("		fantasy_name = ?,		");
		sql.append("		quantity_limit = ?, 	");
		sql.append("		description = ?,		");
		sql.append("		viewer_id = ? 			");
		sql.append("WHERE 	id = ?;					");

		Object[] args = { establishment.getDocumentNumber(),
				establishment.getCompanyName(), establishment.getFantasyName(),
				establishment.getDescription(), establishment.getViewerId(), establishment.getId() };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * Delete the specified Establishment
	 * 
	 * @param id
	 * @return The number of affected rows by the operation
	 * @throws DataAccessException
	 */
	public int deleteEstablishment(long id) throws DataAccessException {
		Assert.notNull(id, "ID is null");

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM establishment WHERE id = ?");

		Object[] args = { id };

		return jdbcTemplate.update(sql.toString(), args);
	}

	/**
	 * List all registered establishments
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<Establishment> getListEstablishmentMap()
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		document_number,    ");
		sql.append("		fantasy_name, 		");
		sql.append("   		quantity_limit, 	");
		sql.append("   		company_name, 		");
		sql.append("   		description,		");
		sql.append("   		viewer_id			");
		sql.append("FROM 	establishment		");

		List<Establishment> list = jdbcTemplateReader.query(sql.toString(),
				new EstablishmentRowMapper());
		return list;
	}

	/**
	 * Get the specified establishment
	 * 
	 * @param id
	 *            The ID of the establishment
	 * @return The establishment
	 * @throws DataAccessException
	 */
	public Establishment getEstablishmentMap(long id)
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	id, 				");
		sql.append("		document_number,    ");
		sql.append("		fantasy_name, 		");
		sql.append("   		quantity_limit, 	");
		sql.append("   		company_name, 		");
		sql.append("   		description,		");
		sql.append("   		viewer_id			");
		sql.append("FROM 	establishment		");
		sql.append("WHERE 	id = ?				");

		Object args[] = { id };

		List<Establishment> list = jdbcTemplateReader.query(sql.toString(), args,
				new EstablishmentRowMapper());

		return !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * Get an establishment with its establishment types
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public Establishment getEstablishmentWithTypesMap(long id)
			throws DataAccessException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	e.id, 											");
		sql.append("		e.document_number,								");
		sql.append("		e.fantasy_name, 								");
		sql.append("   		e.quantity_limit, 								");
		sql.append("   		e.company_name, 								");
		sql.append("   		e.description,									");
		sql.append("   		e.viewer_id,									");
		sql.append("   		ete.id as establishment_establishment_type_id,	");
		sql.append("   		et.id as establishment_type_id,					");
		sql.append("   		et.name as establishment_type_name,				");
		sql.append("   		et.viewer_id as establishment_type_viewer_id	");
		sql.append("FROM 	establishment e									");
		sql.append("INNER JOIN establishment_establishment_type ete 		");
		sql.append("		ON e.id = ete.establishment						");
		sql.append("INNER JOIN establishment_type et 						");
		sql.append("		ON et.id = ete.establishment_type				");
		sql.append("WHERE 	e.id = ?										");

		Object args[] = { id };

		List<Establishment> list = jdbcTemplateReader.query(sql.toString(), args,
				new EstablishmentWithTypesRowMapper());

		return !list.isEmpty() ? list.get(0) : null;
	}
	
}
