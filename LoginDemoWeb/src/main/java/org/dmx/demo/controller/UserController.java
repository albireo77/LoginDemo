package org.dmx.demo.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.dmx.demo.domain.User;
import org.dmx.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
 
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/user/new", method = RequestMethod.GET)
	public @ResponseBody User getNewUser() {
		return new User();	
	}
	
	@RequestMapping(value= "/user/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("username") String userName) {
		
		User user = userService.getByUsername(userName);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		byte[] bytes = Base64.getEncoder().encode(user.getImage());
		String bytesEncoded = new String(bytes, StandardCharsets.UTF_8);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = {RequestMethod.PUT, RequestMethod.POST})
	public ResponseEntity<User> saveUser(@RequestBody User user,
										 @RequestParam(value = "image1", required = false) MultipartFile image) throws IOException {
		if (image != null && !image.isEmpty()) {
			if ("image/jpeg".equals(image.getContentType())) {
				user.setImage(image.getBytes());
			} else {
				throw new IOException("Only JPEG files are accepted for image");
			}
		}
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}
}