package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ganix.establishment.persistence.dao.DefaultScheduleDAO;
import br.com.ganix.establishment.persistence.entity.DefaultSchedule;
import br.com.ganix.establishment.persistence.entity.Establishment;

public class DefaultScheduleDAOTest extends GenericTest {

	private DefaultScheduleDAO defaultScheduleDAO;

	@Before
	public void init() {		
		defaultScheduleDAO = applicationContext.getBean("defaultScheduleDAO", DefaultScheduleDAO.class);
	}

	@After
	public void destroy() {
		defaultScheduleDAO = null;
	}
	
	private long insertTemporaryObject() {
		try {
			DefaultSchedule defaultSchedule = new DefaultSchedule();
			Timestamp openingTime = Timestamp.valueOf("2015-01-01 17:00:00");
			Timestamp closingTime = Timestamp.valueOf("2015-01-01 04:45:00");
			Establishment establishment = new Establishment();
			establishment.setId(1);
			
			defaultSchedule.setEstablishment(establishment);
			defaultSchedule.setOpeningTime(openingTime);
			defaultSchedule.setClosingTime(closingTime);
			defaultSchedule.setViewerId("[TEST] 678");
			defaultSchedule.setDayOfWeek("Saturday");
			defaultSchedule.setTicketValue(123.45);
			
			return defaultScheduleDAO.addDefaultSchedule(defaultSchedule);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Test
	public void testAddDefaultSchedule() {
		DefaultSchedule defaultSchedule = new DefaultSchedule();
		Timestamp openingTime = Timestamp.valueOf("2015-01-01 17:00:00");
		Timestamp closingTime = Timestamp.valueOf("2015-01-01 04:45:00");
		Establishment establishment = new Establishment();
		establishment.setId(1);
		defaultSchedule.setOpeningTime(openingTime);
		defaultSchedule.setClosingTime(closingTime);
		defaultSchedule.setDayOfWeek("Tuesday");
		defaultSchedule.setViewerId("678");
		defaultSchedule.setTicketValue(123.45);
		defaultSchedule.setEstablishment(establishment);
		long id = defaultScheduleDAO.addDefaultSchedule(defaultSchedule);
		assertTrue(id > 0);
	}

	@Test
	public void testUpdateDefaultSchedule() {
		long id = insertTemporaryObject();
		DefaultSchedule defaultSchedule = defaultScheduleDAO.getDefaultScheduleMap(id);
		defaultSchedule.setViewerId("devil_666");
		Timestamp openingTime = Timestamp.valueOf("2015-01-01 17:00:00");
		Timestamp closingTime = Timestamp.valueOf("2015-01-01 04:45:00");
		defaultSchedule.setOpeningTime(openingTime);
		defaultSchedule.setClosingTime(closingTime);
		defaultSchedule.setDayOfWeek("Saturday");
		defaultSchedule.setTicketValue(123.45);
		int rowsAffected = defaultScheduleDAO.updateDefaultSchedule(defaultSchedule);
		assertTrue(rowsAffected == 1);
	}

	@Test
	public void testDeleteDefaultSchedule() {
		long id = insertTemporaryObject();
		int rowsAffected = defaultScheduleDAO.deleteDefaultSchedule(id);
		assertTrue(rowsAffected == 1);
	}

	@Test
	public void testGetListDefaultScheduleMap() {
		insertTemporaryObject();
		List<DefaultSchedule> list = defaultScheduleDAO.getListDefaultScheduleMap();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetListDefaultScheduleEstablishment() {
		insertTemporaryObject();
		List<DefaultSchedule> list = defaultScheduleDAO.getListDefaultScheduleEstablishment(1);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetDefaultScheduleMap() {
		long id = insertTemporaryObject();
		DefaultSchedule defaultSchedule = defaultScheduleDAO.getDefaultScheduleMap(id);
		assertNotNull(defaultSchedule);
	}

}
