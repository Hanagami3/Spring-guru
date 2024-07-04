package be.hanagami.springframework.services.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("PROD")
@Service
public class EnvironmentServiceProduction implements EnvironmentService{
    @Override
    public String getEnvironment() {
        return "prod";
    }
}
