package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ganix.establishment.persistence.dao.FeatureGroupDAO;
import br.com.ganix.establishment.persistence.entity.FeatureGroup;

public class FeatureGroupDAOTest extends GenericTest {

	private FeatureGroupDAO featureGroupDAO;
	
	@Before
	public void init(){
		featureGroupDAO = applicationContext.getBean("featureGroupDAO", FeatureGroupDAO.class);
	}
	
	@After
	public void destroy(){
		featureGroupDAO = null;
	}
	
	private FeatureGroup insertTestObject(){
		FeatureGroup featureGroup = new FeatureGroup();
		featureGroup.setName("[TEST] temp");
		featureGroup.setViewerId("666");
		long id = featureGroupDAO.addFeatureGroup(featureGroup);
		featureGroup.setId(id);
		
		return featureGroup;
	}
	
	@Test
	public void testAddFeatureGroup() {
		FeatureGroup featureGroup = new FeatureGroup();
		featureGroup.setName("[TEST] insert");
		featureGroup.setViewerId("0");
		long id = featureGroupDAO.addFeatureGroup(featureGroup);
		assertTrue(id > 0);
	}

	@Test
	public void testUpdateFeatureGroup() {
		FeatureGroup featureGroup = insertTestObject();
		featureGroup.setName("[TEST] update");
		int rowsAffected = featureGroupDAO.updateFeatureGroup(featureGroup);
		assertTrue(rowsAffected == 1);
	}

	@Test
	public void testDeleteFeatureGroup() {
		long id = insertTestObject().getId();
		int rowsAffected = featureGroupDAO.deleteFeatureGroup(id);
		assertTrue(rowsAffected == 1);
	}

	@Test
	public void testGetFeatureGroupMap() {
		long id = insertTestObject().getId();
		FeatureGroup featureGroup = featureGroupDAO.getFeatureGroupMap(id);
		assertNotNull(featureGroup);
	}

	@Test
	public void testGetListFeatureGroupMap() {
		insertTestObject();
		List<FeatureGroup> list = featureGroupDAO.getListFeatureGroupMap();
		assertFalse(list.isEmpty());
	}

}
