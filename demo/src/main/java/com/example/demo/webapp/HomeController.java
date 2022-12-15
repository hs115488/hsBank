package com.example.demo.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){ return "index"; }

    @RequestMapping("/depositForm")
    public String depositForm(){
        return "depositForm";
    }

    @RequestMapping("/withdrawForm")
    public String withdrawForm(){ return "withdrawForm"; }
}
