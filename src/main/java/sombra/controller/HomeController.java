package sombra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String homePage() throws IOException{
		return "home";
	}
}
