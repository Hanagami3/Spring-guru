package be.hanagami.springframework.services.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("UAT")
@Service
public class EnvironmentServiceUserAcceptanceTesting implements EnvironmentService{
    @Override
    public String getEnvironment() {
        return "uat";
    }
}
