package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import br.com.ganix.establishment.persistence.dao.EstablishmentDAO;
import br.com.ganix.establishment.persistence.dao.EstablishmentTypeDAO;
import br.com.ganix.establishment.persistence.entity.Establishment;
import br.com.ganix.establishment.persistence.entity.EstablishmentType;
import br.com.ganix.establishment.persistence.entity.EstablishmentTypeEstablishment;
import br.com.ganix.establishment.persistence.exceptions.GanixEstablishmentPersistenceException;

public class EstablishmentDAOTest extends GenericTest {

	private EstablishmentDAO establishmentDAO;

	@Before
	public void init() {
		establishmentDAO = applicationContext.getBean("establishmentDAO",
				EstablishmentDAO.class);
	}

	@After
	public void destroy() {
		establishmentDAO = null;
	}
	
	private EstablishmentType insertTestEstablishmentType(){
		EstablishmentTypeDAO dao = applicationContext.getBean("establishmentTypeDAO", EstablishmentTypeDAO.class);
		EstablishmentType establishmentType = new EstablishmentType();
		establishmentType.setName("[TEST]");
		establishmentType.setViewerId("678");
		long id = dao.addEstablishmentType(establishmentType);
		establishmentType.setId(id);
		dao = null;
		return establishmentType;
	}

	private Establishment insertTestObject() throws DataAccessException, GanixEstablishmentPersistenceException {
		Establishment establishment = new Establishment();

		establishment.setDocumentNumber(12345678900012L);
		establishment.setCompanyName("[Test ADD] company");
		establishment.setFantasyName("[Test ADD] fantasy name");
		establishment.setQuantityLimit(60666);
		establishment.setViewerId("678");
		establishment.setDescription("Description of establishment with special character as á é and Sant'nna");

		long id = establishmentDAO.addEstablishment(establishment);
		assertTrue(id > 0);
		establishment.setId(id);
		return establishment;
	}

	@Test
	public void testAddEstablishment() {
		Establishment establishment = new Establishment();

		establishment.setDocumentNumber(12345678900012L);
		establishment.setCompanyName("[Test ADD] company");
		establishment.setFantasyName("[Test ADD] fantasy name");
		establishment.setQuantityLimit(60666);
		establishment.setViewerId("678");
		establishment.setDescription("Description of establishment with special character as ão á é and Sant'nna");

		try {
			long id = establishmentDAO.addEstablishment(establishment);
			assertTrue(id > 0);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail("GanixEstablishmentPersistenceException: " + e.getMessage());
		}
	}
	
	@Test
	public void AddEstablishmentWithTypes(){
		Establishment establishment = new Establishment();

		establishment.setDocumentNumber(12345678900012L);
		establishment.setCompanyName("[Test ADD] company");
		establishment.setFantasyName("[Test ADD] fantasy name");
		establishment.setQuantityLimit(60666);
		establishment.setViewerId("678");
		establishment.setDescription("Description of establishment with special character as á é ú and Sant'nna");
		
		EstablishmentTypeEstablishment establishmentTypeEstablishment = new EstablishmentTypeEstablishment();
		establishmentTypeEstablishment.setEstablishment(establishment);
		establishmentTypeEstablishment.setEstablishmentType(insertTestEstablishmentType());
		establishment.setEstablishmentTypes(new ArrayList<EstablishmentTypeEstablishment>());
		establishment.getEstablishmentTypes().add(establishmentTypeEstablishment);
		
		try {
			long id = establishmentDAO.addEstablishmentWithTypes(establishment);
			assertTrue(id > 0);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail("GanixEstablishmentPersistenceException: " + e.getMessage());
		}
	}

	@Test
	public void testUpdateEstablishment() {
		try {
			Establishment establishment = insertTestObject();
			establishment.setDocumentNumber(666666666666L);
			establishment.setCompanyName("[TEST UPDATE] company");
			int affectedRows = establishmentDAO.updateEstablishment(establishment);
			assertTrue(affectedRows == 1);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail("GanixEstablishmentPersistenceException: " + e.getMessage());
		}
	}

	@Test
	public void testDeleteEstablishment() {
		try {
			Establishment establishment = insertTestObject();
			int affectedRows = establishmentDAO.deleteEstablishment(establishment.getId());
			assertTrue(affectedRows == 1);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail("GanixEstablishmentPersistenceException: " + e.getMessage());
		}
		
	}

	@Test
	public void testGetListEstablishmentMap() {
		List<Establishment> list = establishmentDAO.getListEstablishmentMap();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetEstablishmentMap() {
		try {
			long id = insertTestObject().getId();
			Establishment establishment = establishmentDAO.getEstablishmentMap(id);
			assertNotNull(establishment);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail("GanixEstablishmentPersistenceException: " + e.getMessage());
		}
	}

	@Test
	public void testGetEstablishmentWithTypesMap() {
		try {
			Establishment establishment = establishmentDAO.getEstablishmentWithTypesMap(1);
			assertFalse(establishment.getEstablishmentTypes().isEmpty());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		}
	}
}
