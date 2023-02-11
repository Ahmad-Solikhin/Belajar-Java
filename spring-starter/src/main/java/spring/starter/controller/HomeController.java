package spring.starter.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @GetMapping("/home")
    //Bisa pake cara dibawah
    //@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("name", "Gayuh");
        return "home";
    }

}
