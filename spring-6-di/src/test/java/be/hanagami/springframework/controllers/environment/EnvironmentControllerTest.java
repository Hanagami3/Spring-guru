package be.hanagami.springframework.controllers.environment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

//erreur si on ne rajoute pas EN
@ActiveProfiles({"PROD", "EN"})
@SpringBootTest
class EnvironmentControllerTest {

    @Autowired
    EnvironmentController environmentController;
    @Test
    void getEnvironment() {
        System.out.println(environmentController.getEnvironment());
    }
}