package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import br.com.ganix.establishment.persistence.dao.ControlIDDAO;
import br.com.ganix.establishment.persistence.entity.ControlID;
import br.com.ganix.establishment.persistence.exceptions.GanixEstablishmentPersistenceException;

public class ControlIDDAOTest extends GenericTest {

	private ControlIDDAO controlIDDAO;
	private String tempTableName;

	@Before
	public void init() {
		controlIDDAO = applicationContext.getBean("controlIDDAO",
				ControlIDDAO.class);
		tempTableName = "TEST_devils_table" + Math.random();
	}

	@After
	public void destroy() {
		controlIDDAO = null;
	}

	private void insertTemporaryObject() {
		ControlID controlID = new ControlID();
		controlID.setTableName(tempTableName);
		controlID.setNextValue(666);
		controlIDDAO.addControlID(controlID);
	}

	@Test
	public void testGetNextValue() {
		try {
			insertTemporaryObject();
			long nextValue = controlIDDAO.getNextValue(tempTableName);
			assertTrue(nextValue > 0);
		} catch (DataAccessException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (GanixEstablishmentPersistenceException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAddControlID() {
		ControlID controlID = new ControlID();
		controlID.setTableName("DEVILTABLE" + Math.random());
		controlID.setNextValue(666);
		int rowsAffected = controlIDDAO.addControlID(controlID);
		assertTrue(rowsAffected > 0);
	}

	@Test
	public void testDeleteControlID() {
		insertTemporaryObject();
		int rowsAffected = controlIDDAO.deleteControlID(tempTableName);
		assertTrue(rowsAffected > 0);
	}
}
