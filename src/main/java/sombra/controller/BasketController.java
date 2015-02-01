package sombra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @RequestMapping("")
    public String basket(ModelMap modelMap, Principal user){
        if(user != null){
            modelMap.addAttribute("user", user.getName());
        }
        modelMap.addAttribute("active", "basket");
        return "basket";
    }
}
