package br.com.ganix.establishment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ganix.establishment.persistence.dao.AlternateScheduleDAO;
import br.com.ganix.establishment.persistence.entity.AlternateSchedule;
import br.com.ganix.establishment.persistence.entity.Establishment;

public class AlternateScheduleDAOTest extends GenericTest {

	private AlternateScheduleDAO alternateScheduleDAO;

	@Before
	public void init() {
		alternateScheduleDAO = (AlternateScheduleDAO) applicationContext
				.getBean("alternateScheduleDAO");
	}

	@After
	public void destroy() {
		alternateScheduleDAO = null;
	}

	private long InsertTemporaryObject() {
		try {
			AlternateSchedule alternateSchedule = new AlternateSchedule();
			Timestamp openingTime = Timestamp.valueOf("2015-01-01 17:00:00");
			Timestamp closingTime = Timestamp.valueOf("2015-01-01 04:45:00");
			alternateSchedule.setOpeningTime(openingTime);
			alternateSchedule.setClosingTime(closingTime);
			alternateSchedule.setDistinctDay(getSqlDate("26/02/2015"));
			alternateSchedule.setViewerId("678");
			alternateSchedule.setTicketValue(123.45);
			Establishment establishment = new Establishment();
			establishment.setId(1);
			alternateSchedule.setEstablishment(establishment);
			return alternateScheduleDAO.addAlternateSchedule(alternateSchedule);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Test
	public void testAddAlternateSchedule() {
		try {
			AlternateSchedule alternateSchedule = new AlternateSchedule();
			Timestamp openingTime = Timestamp.valueOf("2015-01-01 17:00:00");
			Timestamp closingTime = Timestamp.valueOf("2015-01-01 04:45:00");
			alternateSchedule.setOpeningTime(openingTime);
			alternateSchedule.setClosingTime(closingTime);
			alternateSchedule.setDistinctDay(getSqlDate("26/02/2015"));
			alternateSchedule.setViewerId("678");
			alternateSchedule.setTicketValue(123.45);
			Establishment establishment = new Establishment();
			establishment.setId(1);
			alternateSchedule.setEstablishment(establishment);
			long id = alternateScheduleDAO
					.addAlternateSchedule(alternateSchedule);
			assertTrue(id > 0);
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdateAlternateSchedule() {
		InsertTemporaryObject();
		AlternateSchedule alternateSchedule = alternateScheduleDAO
				.getListAlternateScheduleMap().get(0);
		Timestamp openingTime = Timestamp.valueOf("2015-01-01 17:00:00");
		Timestamp closingTime = Timestamp.valueOf("2015-01-01 04:45:00");
		alternateSchedule.setOpeningTime(openingTime);
		alternateSchedule.setClosingTime(closingTime);
		alternateSchedule.setViewerId("789");
		alternateSchedule.setTicketValue(123.45);
		long rowsAffected = alternateScheduleDAO
				.updateAlternateSchedule(alternateSchedule);
		assertTrue(rowsAffected > 0);
	}

	@Test
	public void testDeleteAlternateSchedule() {
		long id = InsertTemporaryObject();
		int rowsDeleted = alternateScheduleDAO.deleteAlternateSchedule(id);
		assertTrue(rowsDeleted > 0);
	}

	@Test
	public void testGetListAlternateScheduleMap() {
		List<AlternateSchedule> list = alternateScheduleDAO
				.getListAlternateScheduleMap();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetListAlternateScheduleEstablishment() {
		List<AlternateSchedule> list = alternateScheduleDAO
				.getListAlternateScheduleEstablishment(1);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetAlternateSchedule() {
		long id = InsertTemporaryObject();
		AlternateSchedule alternateSchedule = alternateScheduleDAO
				.getAlternateSchedule(id);
		assertNotNull(alternateSchedule);
	}

}
