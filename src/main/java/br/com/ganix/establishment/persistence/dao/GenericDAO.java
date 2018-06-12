package br.com.ganix.establishment.persistence.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class GenericDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6546021014601509672L;

	@Autowired
	@Qualifier("pgjdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("pgjdbcTemplateReader")
	protected JdbcTemplate jdbcTemplateReader;

	/**
	 * Execute an insert operation and return the inserted id.
	 * 
	 * @param sql
	 *            The insert statement
	 * @param args
	 *            The parameters of the row to be inserted
	 * @return The inserted id
	 */
	protected Number insertReturnigID(final String sql, final Object args[]) {
		
		String pkColumn = "id";
		return insertReturnigID(sql, args, pkColumn);
	}

	/**
	 * Execute an insert operation and return the inserted id.
	 * @param sql The insert statement
	 * @param args The parameters of the row to be inserted
	 * @param pkColumns The primary key column to be returned
	 * @return
	 */
	protected Number insertReturnigID(final String sql, final Object args[], String pkColumn) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String pkColumns[] = new String[] { pkColumn };
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						sql.toString(), pkColumns);

				for (int i = 0; i < args.length; i++) {
					ps.setObject((i + 1), args[i]);
				}
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey();
	}
}
