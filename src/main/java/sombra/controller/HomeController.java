package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sombra.dao.IUserDAO;

import java.io.IOException;
import java.security.Principal;

@Controller
public class HomeController {

	@Autowired
	IUserDAO userDAO;

	@RequestMapping(value="/")
	public String test(ModelMap model, Principal user) throws IOException{
		if(user != null) {
			model.addAttribute("user", user.getName());
		}
		return "home";
	}
}
