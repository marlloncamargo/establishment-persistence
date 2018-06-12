package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ganix.establishment.persistence.dao.FeatureDAO;
import br.com.ganix.establishment.persistence.entity.Feature;
import br.com.ganix.establishment.persistence.entity.FeatureGroup;

public class FeatureDAOTest extends GenericTest {

	private FeatureDAO featureDAO;
	
	@Before
	public void init(){
		featureDAO = applicationContext.getBean("featureDAO", FeatureDAO.class);
	}
	
	@After
	public void destroy(){
		featureDAO = null;
	}
	
	private Feature insertTestObject(){
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
	public void testAddFeature() {
		Feature feature = new Feature();
		feature.setAbbreviation("[TEST]abbr");
		feature.setName("[TEST] TEMP FEATURE");
		FeatureGroup featureGroup = new FeatureGroup();
		featureGroup.setId(1);
		feature.setFeatureGroup(featureGroup);
		feature.setViewerId("666");
		long id = featureDAO.addFeature(feature);
		
		assertTrue(id > 0);
	}

	@Test
	public void testUpdateFeature() {
		Feature feature = insertTestObject();
		feature.setName("[TEST UPDATE] temp feature");
		int rowsAffected = featureDAO.updateFeature(feature);
		assertTrue(rowsAffected == 1);
	}

	@Test
	public void testDeleteFeature() {
		long id = insertTestObject().getId();
		int rowsAffected = featureDAO.deleteFeature(id);
		assertTrue(rowsAffected == 1);		
	}

	@Test
	public void testGetListFeatureMap() {
		List<Feature> list = featureDAO.getListFeatureMap();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetFeatureMap() {
		long id = insertTestObject().getId();
		Feature feature = featureDAO.getFeatureMap(id);
		assertNotNull(feature);
	}
}
