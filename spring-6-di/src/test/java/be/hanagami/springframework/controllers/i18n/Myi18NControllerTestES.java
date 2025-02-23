package be.hanagami.springframework.controllers.i18n;

import be.hanagami.springframework.services.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("ES")
@SpringBootTest
class Myi18NControllerTestES {

    @Autowired
    Myi18NController myi18NController;


    @Test
    void sayHello() {
        System.out.println(myi18NController.sayHello());
    }
}