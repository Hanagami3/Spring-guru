package be.hanagami.springframework.controllers;

import be.hanagami.springframework.services.GreetingService;
import be.hanagami.springframework.services.GreetingServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private final GreetingService greetingService;

    public MyController() {
        this.greetingService = new GreetingServiceImpl();
    }

//    public String sayHello(){
//        System.out.println("I'm in the controller");
//
//        return "Hello World";
//    }

    public String sayHello(){
        System.out.println("I'm in the controller");

        return greetingService.sayGreeting();
    }

    public void beforeInit(){
        System.out.println("## - Before Init - Called by Bean Post Processor");
    }

    public void afterInit(){
        System.out.println("## - After Init - Called by Bean Post Processor");
    }
}
