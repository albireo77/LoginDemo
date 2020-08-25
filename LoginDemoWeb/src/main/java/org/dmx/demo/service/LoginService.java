package org.dmx.demo.service;

import org.dmx.demo.domain.LoginBean;
import org.dmx.demo.domain.LoginResult;
import org.dmx.demo.domain.User;

public interface LoginService {
	
	LoginResult login(LoginBean loginBean);
	
	User getUser();

}
