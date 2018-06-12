package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ganix.establishment.persistence.dao.EstablishmentTypeDAO;
import br.com.ganix.establishment.persistence.entity.EstablishmentType;

public class EstablishmentTypeDAOTest extends GenericTest {

	private EstablishmentTypeDAO establishmentTypeDAO;
	
	@Before
	public void init(){
		establishmentTypeDAO = applicationContext.getBean("establishmentTypeDAO", EstablishmentTypeDAO.class);
	}
	
	@After
	public void destroy(){
		establishmentTypeDAO = null;
	}
	
	private EstablishmentType insertTestObject(){
		EstablishmentType establishmentType = new EstablishmentType();
		establishmentType.setName("[TEST] temp balada");
		establishmentType.setViewerId("666");
		long id = establishmentTypeDAO.addEstablishmentType(establishmentType);
		establishmentType.setId(id);
		return establishmentType;
	}
	
	@Test
	public void testAddEstablishmentType() {
		EstablishmentType establishmentType = new EstablishmentType();
		establishmentType.setName("[TEST] BALADA");
		establishmentType.setViewerId("666");
		
		long id = establishmentTypeDAO.addEstablishmentType(establishmentType);
		assertTrue(id > 0);
	}

	@Test
	public void testUpdateEstablishmentType() {
		EstablishmentType establishmentType = insertTestObject();
		establishmentType.setName("[TEST UPDATE] temp balada");
		int rowsAffected = establishmentTypeDAO.updateEstablishmentType(establishmentType);
		assertTrue(rowsAffected == 1);
	}

	@Test
	public void testDeleteEstablishmentType() {
		long id = insertTestObject().getId();
		int rowsAffected = establishmentTypeDAO.deleteEstablishmentType(id);
		assertTrue(rowsAffected == 1);
	}

	@Test
	public void testGetListEstablishmentTypeMap() {
		insertTestObject();
		List<EstablishmentType> list = establishmentTypeDAO.getListEstablishmentTypeMap();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetEstablishmentTypeMap() {
		long id = insertTestObject().getId();
		EstablishmentType establishmentType = establishmentTypeDAO.getEstablishmentTypeMap(id);
		assertNotNull(establishmentType);
	}

}
