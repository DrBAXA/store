package sombra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String test(ModelMap model, Principal user) throws IOException{
		if(user != null) {
			model.addAttribute("user", user.getName());
		}
		return "home";
	}
}
