package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.AlternateSchedule;
import br.com.ganix.establishment.persistence.entity.Establishment;

public class AlternateScheduleRowMapper implements RowMapper<AlternateSchedule> {

	@Override
	public AlternateSchedule mapRow(ResultSet rs, int i) throws SQLException {
		AlternateSchedule alternateSchedule = new AlternateSchedule();
		alternateSchedule.setId(rs.getLong("id"));
		alternateSchedule.setDistinctDay(rs.getDate("distinct_day"));
		alternateSchedule.setOpeningTime(rs.getTimestamp("opening_time"));
		alternateSchedule.setClosingTime(rs.getTimestamp("closing_time"));
		alternateSchedule.setViewerId(rs.getString("viewer_id"));
		alternateSchedule.setTicketValue(rs.getDouble("ticket_value"));
		
		Establishment establishment = new Establishment();
		establishment.setId(rs.getLong("establishment"));
		alternateSchedule.setEstablishment(establishment);

		return alternateSchedule;
	}

}
