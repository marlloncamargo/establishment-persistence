package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.DefaultSchedule;
import br.com.ganix.establishment.persistence.entity.Establishment;

public class DefaultScheduleRowMapper implements RowMapper<DefaultSchedule> {

	@Override
	public DefaultSchedule mapRow(ResultSet rs, int i) throws SQLException {
		DefaultSchedule defaultSchedule = new DefaultSchedule();
		Establishment establishment = new Establishment();
		
		defaultSchedule.setId(rs.getLong("id"));
		defaultSchedule.setDayOfWeek(rs.getString("day_of_week"));
		defaultSchedule.setOpeningTime(rs.getTimestamp("opening_time"));
		defaultSchedule.setClosingTime(rs.getTimestamp("closing_time"));
		defaultSchedule.setViewerId(rs.getString("viewer_id"));
		defaultSchedule.setTicketValue(rs.getDouble("ticket_value"));

		establishment.setId(rs.getLong("establishment"));
		defaultSchedule.setEstablishment(establishment);

		return defaultSchedule;
	}

}
