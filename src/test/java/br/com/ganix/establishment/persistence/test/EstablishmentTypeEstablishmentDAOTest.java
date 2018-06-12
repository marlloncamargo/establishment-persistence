package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import br.com.ganix.establishment.persistence.dao.EstablishmentDAO;
import br.com.ganix.establishment.persistence.dao.EstablishmentTypeDAO;
import br.com.ganix.establishment.persistence.dao.EstablishmentTypeEstablishmentDAO;
import br.com.ganix.establishment.persistence.entity.Establishment;
import br.com.ganix.establishment.persistence.entity.EstablishmentType;
import br.com.ganix.establishment.persistence.entity.EstablishmentTypeEstablishment;
import br.com.ganix.establishment.persistence.exceptions.GanixEstablishmentPersistenceException;

public class EstablishmentTypeEstablishmentDAOTest extends GenericTest {

	private EstablishmentTypeEstablishmentDAO establishmentTypeEstablishmentDAO;

	@Before
	public void init() {
		establishmentTypeEstablishmentDAO = applicationContext.getBean(
				"establishmentTypeEstablishmentDAO",
				EstablishmentTypeEstablishmentDAO.class);
	}

	@After
	public void destroy() {
		establishmentTypeEstablishmentDAO = null;
	}

	private EstablishmentTypeEstablishment insertTestObject()
			throws DataAccessException, GanixEstablishmentPersistenceException {
		EstablishmentTypeEstablishment establishmentTypeEstablishment = new EstablishmentTypeEstablishment();
		establishmentTypeEstablishment
				.setEstablishment(insertTestEstablishment());
		establishmentTypeEstablishment
				.setEstablishmentType(insertTestEstablishmentType("[TEST] temp"));
		long id = establishmentTypeEstablishmentDAO
				.addEstablishmentTypeEstablishment(establishmentTypeEstablishment);
		establishmentTypeEstablishment.setId(id);
		return establishmentTypeEstablishment;
	}

	private Establishment insertTestEstablishment() throws DataAccessException,
			GanixEstablishmentPersistenceException {
		EstablishmentDAO establishmentDAO = applicationContext.getBean(
				"establishmentDAO", EstablishmentDAO.class);
		Establishment establishment = new Establishment();

		establishment.setDocumentNumber(12345678900012L);
		establishment.setCompanyName("[Test ADD] company establishment type");
		establishment.setFantasyName("[Test ADD] fantasy name");
		establishment.setQuantityLimit(60666);
		establishment.setViewerId("666");
		establishment.setDescription("Description of establishment with special character as á é and Sant'nna");

		long id = establishmentDAO.addEstablishment(establishment);
		assertTrue(id > 0);
		establishment.setId(id);
		establishmentDAO = null;
		return establishment;
	}

	private EstablishmentType insertTestEstablishmentType(String name) {
		EstablishmentTypeDAO dao = applicationContext.getBean(
				"establishmentTypeDAO", EstablishmentTypeDAO.class);
		EstablishmentType establishmentType = new EstablishmentType();
		establishmentType.setName(name);
		establishmentType.setViewerId("666");
		long id = dao.addEstablishmentType(establishmentType);
		establishmentType.setId(id);
		dao = null;
		return establishmentType;
	}

	@Test
	public void testAddEstablishmentTypeEstablishment() {
		EstablishmentTypeEstablishment establishmentTypeEstablishment = new EstablishmentTypeEstablishment();
		Establishment establishment;
		try {
			establishment = insertTestEstablishment();
			EstablishmentType establishmentType = insertTestEstablishmentType("[TEST] Add");
			establishmentTypeEstablishment.setEstablishment(establishment);
			establishmentTypeEstablishment
					.setEstablishmentType(establishmentType);

			long id = establishmentTypeEstablishmentDAO
					.addEstablishmentTypeEstablishment(establishmentTypeEstablishment);
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
	public void testUpdateEstablishmentTypeEstablishment() {
		try {
			EstablishmentTypeEstablishment establishmentTypeEstablishment = insertTestObject();
			EstablishmentType establishmentType = insertTestEstablishmentType("[TEST] update");
			establishmentTypeEstablishment
					.setEstablishmentType(establishmentType);

			int rowsAffected = establishmentTypeEstablishmentDAO
					.updateEstablishmentTypeEstablishment(establishmentTypeEstablishment);
			assertTrue(rowsAffected == 1);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail("GanixEstablishmentPersistenceException: " + e.getMessage());
		}

	}

	@Test
	public void testDeleteEstablishmentTypeEstablishment() {
		try {
			long id = insertTestObject().getId();
			int rowsAffected = establishmentTypeEstablishmentDAO
					.deleteEstablishmentTypeEstablishment(id);
			assertTrue(rowsAffected == 1);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail("GanixEstablishmentPersistenceException: " + e.getMessage());
		}
	}

	@Test
	public void testGetEstablishmentTypeEstablishmentMap() {
		List<EstablishmentTypeEstablishment> list = establishmentTypeEstablishmentDAO
				.getEstablishmentTypeEstablishmentMap();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetEstablishmentTypeEstablishmentMapLongLong() {
		try {
			EstablishmentTypeEstablishment temp = insertTestObject();
			long establishmentID = temp.getEstablishment().getId();
			long establishmentTypeID = temp.getEstablishmentType().getId();

			List<EstablishmentTypeEstablishment> list = establishmentTypeEstablishmentDAO
					.getEstablishmentTypeEstablishmentMap(establishmentID,
							establishmentTypeID);
			assertFalse(list.isEmpty());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail("GanixEstablishmentPersistenceException: " + e.getMessage());
		}
	}

}
