package be.hanagami.springframework.controllers;

import be.hanagami.springframework.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


@Controller
public class PropertyInjectedController {

    @Qualifier("propertyGreetingService")
    //not recommended
    @Autowired
    GreetingService greetingService;

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
