package be.hanagami.springframework.services.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("QA")
@Service
public class EnvironmentServiceQualityAssurance implements EnvironmentService{

    @Override
    public String getEnvironment() {
        return "qa";
    }
}
