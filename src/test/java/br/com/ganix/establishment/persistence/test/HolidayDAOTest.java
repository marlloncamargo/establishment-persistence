package br.com.ganix.establishment.persistence.test;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import br.com.ganix.establishment.persistence.dao.HolidayDAO;
import br.com.ganix.establishment.persistence.entity.Holiday;



public class HolidayDAOTest extends GenericTest {

	private HolidayDAO holidayDAO;

	@Before
	public void init() {
		holidayDAO = (HolidayDAO) applicationContext.getBean("holidayDAO");
	}

	@After
	public void destroy() {
		holidayDAO = null;
	}
	
	private long insertTemporaryObject(){
		try {
			Holiday holiday = new Holiday();
			Date sqlDate = getSqlDate("25/12/2015");
			holiday.setHolidayDate(sqlDate);
			holiday.setName("[TEST TEMP] Natal");
			holiday.setViewerId("666");
			long id = holidayDAO.addHoliday(holiday);
			
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Test
	public void testAddHoliday() {

		try {
			Holiday holiday = new Holiday();
			Date sqlDate = getSqlDate("25/12/2015");
			holiday.setHolidayDate(sqlDate);
			holiday.setName("[TEST] Natal");
			holiday.setViewerId("0");
			long id = holidayDAO.addHoliday(holiday);
			assertTrue(id > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUpdateHoliday() {
		try {
			Holiday holiday = new Holiday();
			Holiday holidayTemp = holidayDAO.getListHolidayMap().get(0);
			holiday.setId(holidayTemp.getId());
			holiday.setName("[TEST UPDATE] Natal!");
			holiday.setHolidayDate(getSqlDate("25/12/2015"));
			holiday.setViewerId("0");
			int rowsAffected = holidayDAO.updateHoliday(holiday);
			assertTrue(rowsAffected > 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetListHolidayMap() {
		List<Holiday> list = holidayDAO.getListHolidayMap();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetHolidayMap() {
		long id = insertTemporaryObject();
		Holiday holiday = holidayDAO.getHolidayMap(id);
		assertNotNull(holiday);
	}

	@Test
	public void testDeleteHoliday() {
		long id = insertTemporaryObject();
		int rowsAffected = holidayDAO.deleteHoliday(id);
		assertTrue(rowsAffected > 0);
	}
}
