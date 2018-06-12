package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.Holiday;

public class HolidayRowMapper implements RowMapper<Holiday> {

	@Override
	public Holiday mapRow(ResultSet rs, int i) throws SQLException {
		Holiday holiday = new Holiday();
		holiday.setId(rs.getLong("id"));
		holiday.setName(rs.getString("name"));
		holiday.setHolidayDate(rs.getDate("holiday_date"));

		return holiday;
	}

}
