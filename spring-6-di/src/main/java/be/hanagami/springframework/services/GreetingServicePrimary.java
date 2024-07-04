package be.hanagami.springframework.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


//comme il y a deux implémentation de l'interface service, on doit écrire le primary pour donner une
//Sinon, il y a une erreur
@Primary
@Service
public class GreetingServicePrimary implements GreetingService{

    @Override
    public String sayGreeting() {
        return "Hello from the Primary Bean";
    }
}
