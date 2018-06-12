package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import br.com.ganix.establishment.persistence.dao.EstablishmentDAO;
import br.com.ganix.establishment.persistence.dao.FeatureDAO;
import br.com.ganix.establishment.persistence.dao.FeatureEstablishmentDAO;
import br.com.ganix.establishment.persistence.entity.Establishment;
import br.com.ganix.establishment.persistence.entity.Feature;
import br.com.ganix.establishment.persistence.entity.FeatureEstablishment;
import br.com.ganix.establishment.persistence.entity.FeatureGroup;
import br.com.ganix.establishment.persistence.exceptions.GanixEstablishmentPersistenceException;

public class FeatureEstablishmentDAOTest extends GenericTest {

	private FeatureEstablishmentDAO featureEstablishmentDAO;

	@Before
	public void init() {
		featureEstablishmentDAO = applicationContext.getBean(
				"featureEstablishmentDAO", FeatureEstablishmentDAO.class);
	}

	@After
	public void destroy() {
		featureEstablishmentDAO = null;
	}

	private FeatureEstablishment insertTempObject(String value)
			throws DataAccessException, GanixEstablishmentPersistenceException {
		FeatureEstablishment featureEstablishment = new FeatureEstablishment();
		featureEstablishment.setValue(value);
		featureEstablishment.setEstablishment(insertTestEstablishment());
		featureEstablishment.setFeature(insertTestFeature());
		long id = featureEstablishmentDAO
				.addFeatureEstablishment(featureEstablishment);
		featureEstablishment.setId(id);
		return featureEstablishment;
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

	private Feature insertTestFeature() {
		FeatureDAO featureDAO = applicationContext.getBean("featureDAO",
				FeatureDAO.class);
		Feature feature = new Feature();
		feature.setAbbreviation("[TEST]abbr");
		feature.setName("[TEST] temp feature");
		FeatureGroup featureGroup = new FeatureGroup();
		featureGroup.setId(1);
		feature.setFeatureGroup(featureGroup);
		feature.setViewerId("666");
		long id = featureDAO.addFeature(feature);
		feature.setId(id);

		return feature;
	}

	@Test
	public void testAddFeatureEstablishment() {
		FeatureEstablishment featureEstablishment = new FeatureEstablishment();
		try {
			featureEstablishment.setEstablishment(insertTestEstablishment());
			featureEstablishment.setFeature(insertTestFeature());
			featureEstablishment.setValue("[TEST] insert");

			long id = featureEstablishmentDAO
					.addFeatureEstablishment(featureEstablishment);
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
	public void testUpdateFeatureEstablishment() {
		try {
			FeatureEstablishment featureEstablishment = insertTempObject("[TEST]");
			featureEstablishment.setValue("[TEST] update");
			int rowsAffected = featureEstablishmentDAO
					.updateFeatureEstablishment(featureEstablishment);
			assertTrue(rowsAffected == 1);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteFeatureEstablishment() {
		try {
			long id = insertTempObject("[TEST]").getId();
			int rowsAffected = featureEstablishmentDAO
					.deleteFeatureEstablishment(id);
			assertTrue(rowsAffected == 1);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetFeatureEstablishmentMap() {
		try {
			long id = insertTempObject("[TEST] get").getId();

			FeatureEstablishment featureEstablishment = featureEstablishmentDAO
					.getFeatureEstablishmentMap(id);
			assertNotNull(featureEstablishment);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetListFeatureEstablishmentMap() {

		try {
			FeatureEstablishment featureEstablishment = insertTempObject("[TEST] list");
			List<FeatureEstablishment> list = featureEstablishmentDAO
					.getListFeatureEstablishmentMap(featureEstablishment
							.getEstablishment().getId());
			assertFalse(list.isEmpty());
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail("DataAccessException: " + e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
		}
	}

}
