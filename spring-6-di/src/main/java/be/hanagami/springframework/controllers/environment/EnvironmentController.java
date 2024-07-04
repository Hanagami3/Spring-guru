package be.hanagami.springframework.controllers.environment;

import be.hanagami.springframework.services.environment.EnvironmentService;
import org.springframework.stereotype.Controller;

@Controller
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    public String getEnvironment() {
        return "You are in " + environmentService.getEnvironment() + " Environment";
    }

}
