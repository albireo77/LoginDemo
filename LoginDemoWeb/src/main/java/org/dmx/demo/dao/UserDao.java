package org.dmx.demo.dao;

import java.util.List;

import org.dmx.demo.domain.User;
import org.springframework.dao.DataAccessException;



public interface UserDao {

	User getById(int id) throws DataAccessException;
	
	List<User> getUser(String username) throws DataAccessException;
	
	List<User> getAllUsers() throws DataAccessException;

	User save(User user) throws DataAccessException;

}
