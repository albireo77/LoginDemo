package org.dmx.demo.service;

import java.util.List;

import org.dmx.demo.domain.User;
import org.springframework.dao.DataAccessException;

public interface UserService {
	
	User save(User user);
	
	User getByUsername(String username) throws DataAccessException;
	
	List<User> getAllUsers();

}
