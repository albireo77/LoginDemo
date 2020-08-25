package org.dmx.demo.service.impl;

import org.dmx.demo.domain.LoginBean;
import org.dmx.demo.domain.LoginResult;
import org.dmx.demo.domain.User;
import org.dmx.demo.service.LoginService;
import org.dmx.demo.service.UserService;
import static org.dmx.demo.domain.LoginResult.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserService userService;
	
	private User user;
	
	public LoginResult login(LoginBean loginBean) {
		
		user = userService.getByUsername(loginBean.getUsername());
		if (user == null) {
			return NO_USER;
		}
		if (loginBean.getPassword() == null) {
			return BAD_PASS;
		}
		if (!loginBean.getPassword().equals(user.getPassword())) {
			return BAD_PASS;
		}
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}
}
