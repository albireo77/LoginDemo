package org.dmx.demo.domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginBean {
	
	@Pattern(regexp="^[a-zA-Z0-9._-]{6,8}$", message="Username must contain between 6 and 8 characters. Allowed characters: a-z A-Z 0-9 . _ -")
	private String username;
	
	@Size(min=6, message="Password must have at least 6 characters")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
