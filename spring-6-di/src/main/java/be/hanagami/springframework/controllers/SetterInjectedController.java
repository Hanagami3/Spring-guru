package be.hanagami.springframework.controllers;

import be.hanagami.springframework.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class SetterInjectedController {

    //Not recommended

    private GreetingService greetingService;

    //@Qualifier("setterGreetingBean") --> au choix
    @Autowired
    public void setGreetingService(@Qualifier("setterGreetingBean")
                                       GreetingService greetingService) {
        System.out.println("SetterInjectedController.setGreetingService"); // ou on peut aussi utimiser le débuger (point rouge sur la ligne)
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return greetingService.sayGreeting();
    }

}
