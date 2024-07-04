package be.hanagami.springframework.services.i18n;

import be.hanagami.springframework.services.GreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("ES")
@Service("i18NService")
public class SpanishGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hola Mundi - ES";
    }
}
