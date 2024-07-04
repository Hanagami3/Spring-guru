package be.hanagami.springframework.services.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"DEV", "default"})
@Service
public class EnvironmentServiceDevelopment implements EnvironmentService{

    @Override
    public String getEnvironment() {
        return "dev";
    }
}
