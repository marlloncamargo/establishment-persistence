package br.com.ganix.establishment.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.ganix.establishment.persistence.entity.ControlID;

public class ControlIDRowMapper implements RowMapper<ControlID> {

	@Override
	public ControlID mapRow(ResultSet rs, int i) throws SQLException {
		ControlID controlID = new ControlID();
		controlID.setTableName(rs.getString("table_name"));
		controlID.setNextValue(rs.getLong("next_value"));
		return controlID;
	}

}
