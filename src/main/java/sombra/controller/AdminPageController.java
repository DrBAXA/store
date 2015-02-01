package sombra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String getAdminPage(){
        return "admin";
    }
}
