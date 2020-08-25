package org.dmx.demo.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dmx.demo.domain.User;
import org.dmx.demo.utils.BooleanYNConverter;
import org.springframework.jdbc.core.RowMapper;

public class JdbcUserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int idx) throws SQLException {
		
		User user = new User();
		user.setId(rs.getInt("USER_ID"));
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setBirthDate(rs.getDate("BIRTH_DATE"));
		user.setUsername(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setImage(rs.getBytes("IMAGE"));
		user.setAdmin(BooleanYNConverter.convertStringToBool(rs.getString("IS_ADMIN")));		
		return user;
	}

}
