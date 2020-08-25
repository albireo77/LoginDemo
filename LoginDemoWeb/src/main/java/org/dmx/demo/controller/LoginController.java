package org.dmx.demo.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.dmx.demo.domain.LoginBean;
import org.dmx.demo.domain.LoginResult;
import org.dmx.demo.service.LoginService;
import org.dmx.demo.service.UserService;
//import static org.dmx.demo.domain.LoginResult.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/Login")
public class LoginController {
 
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute("loginBean", new LoginBean());
		return "Login";	
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@Valid LoginBean loginBean, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "Login";
		}
		
		String msg = null;
		
		if (StringUtils.isNotEmpty(loginBean.getUsername())) {				
			LoginResult loginResult = loginService.login(loginBean);
			switch (loginResult) {
				case NO_USER:
					msg = "User \"" + loginBean.getUsername() + "\" does not exist";
					break;
				case BAD_PASS:
					msg = "Incorrect password for user " + loginBean.getUsername();
					break;
				case EXPIRE_PASS:
					msg = "Password expired for user " + loginBean.getUsername();
					break;
				case SUCCESS:
					// add user to session
					loginService.getUser();
					return "Welcome";
			}
		}		
		model.addAttribute("msg", msg);
		return "Login";
   }

}