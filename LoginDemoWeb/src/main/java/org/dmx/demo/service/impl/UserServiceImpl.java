package org.dmx.demo.service.impl;

import java.util.List;

import org.dmx.demo.dao.UserDao;
import org.dmx.demo.domain.User;
import org.dmx.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public User getByUsername(String username) throws DataAccessException {
		
		List<User> users = userDao.getUser(username);
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public List<User> getAllUsers() {

		return userDao.getAllUsers();
	}

}
