package sombra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sombra.dao.IUserDAO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

	@Autowired
	IUserDAO userDAO;

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		userDAO.addUser("user", "user@mail.com", "shbyrfkzkz2");
		return new ModelAndView("home");
	}
}
