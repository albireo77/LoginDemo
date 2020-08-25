package org.dmx.demo.service.impl;

import org.dmx.demo.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.dmx.demo.domain.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserDao userDao;
	
	@InjectMocks
	UserServiceImpl beingTested;

	@Test
	public void test() {
		beingTested.save(new User());
	}

}
