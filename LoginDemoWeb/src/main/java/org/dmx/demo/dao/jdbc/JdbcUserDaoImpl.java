package org.dmx.demo.dao.jdbc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

import org.dmx.demo.dao.UserDao;
import org.dmx.demo.domain.User;
import org.dmx.demo.utils.BooleanYNConverter;

@Repository
public class JdbcUserDaoImpl extends NamedParameterJdbcDaoSupport implements UserDao {
	
	private static final String UPDATE_USER_SQL = "UPDATE USERS SET FIRST_NAME = :firstname, "
			+ "LAST_NAME = :lastname, BIRTH_DATE = :birthdate, IS_ADMIN = :isadmin, "
			+ "USERNAME = :username, PASSWORD = :password, IMAGE = :image WHERE USER_ID = :id";
	
	private static final String INSERT_USER_SQL = "INSERT INTO USERS (USER_ID, FIRST_NAME, LAST_NAME,"
			+ "USERNAME, PASSWORD, BIRTH_DATE, IS_ADMIN, IMAGE) VALUES (USER_SEQ.NEXTVAL, :firstname, :lastname, "
			+ ":username, :password, :birthdate, :isadmin, :image)";
	
	private static final String SELECT_USER_BYID_SQL = "SELECT * FROM USERS WHERE USER_ID = :id";
	
	private static final String SELECT_USER_BYLOGIN_SQL = "SELECT * FROM USERS WHERE USERNAME = :username";
	
	private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM USERS";
	
	@Autowired
	public JdbcUserDaoImpl(DataSource ds) {		
		setDataSource(ds);
	}

	@Override
	public User getById(int id) throws DataAccessException {
		
		Map<String, Object> parms = new HashMap<>();
		parms.put("id", id);
		return getNamedParameterJdbcTemplate().
				queryForObject(SELECT_USER_BYID_SQL, parms, new JdbcUserRowMapper());
	}
	
	@Override
	public List<User> getUser(String username) throws DataAccessException {
		
		Map<String, Object> parms = new HashMap<>();
		parms.put("username", username);
		return getNamedParameterJdbcTemplate().
				query(SELECT_USER_BYLOGIN_SQL, parms, new JdbcUserRowMapper());
	}
	
	@Override
	public List<User> getAllUsers() throws DataAccessException {
		return getNamedParameterJdbcTemplate().
				query(SELECT_ALL_USERS_SQL, new JdbcUserRowMapper());
	}

	@Override
	public User save(User user) throws DataAccessException {
		
		SqlParameterSource params = createUserParameterSource(user);		
		if (user.isNew()) {
			KeyHolder keysHolder = new GeneratedKeyHolder();
			getNamedParameterJdbcTemplate().
				update(INSERT_USER_SQL, params, keysHolder, new String[] {"USER_ID"});
			int id = keysHolder.getKey().intValue();
            user.setId(id);
		} else {
			getNamedParameterJdbcTemplate().update(UPDATE_USER_SQL, params);
		}
		return user;
		
	}
	
    private SqlParameterSource createUserParameterSource(User user) {
    	
    	InputStream is = null;
    	int count = 0;
    	if (user.getImage() != null) {
    		is = new ByteArrayInputStream(user.getImage());
    		count = user.getImage().length;
    	}
    	
        return new MapSqlParameterSource()
            .addValue("id", user.getId())
            .addValue("firstname", user.getFirstName())
            .addValue("lastname", user.getLastName())
            .addValue("username", user.getUsername())
            .addValue("password", user.getPassword())
            .addValue("birthdate", user.getBirthDate())
            .addValue("image", new SqlLobValue(is, count, new DefaultLobHandler()), OracleTypes.BLOB)
            .addValue("isadmin", BooleanYNConverter.convertBoolToString(user.isAdmin()));
    }

}
