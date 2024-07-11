package be.hanagami.spring6restmvc.repositories;

import be.hanagami.spring6restmvc.entities.Beer;
import be.hanagami.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong(){

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer zge<gegrgrgropghreopgrhepkhzge<gegrgrgropghreopgrhepkhzge<gegrgrgropghreopgrhepkhzge<gegrgrgropghreopgrhepkhzge<gegrgrgropghreopgrhepkhzge<gegrgrgropghreopgrhepkh")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("564641415")
                    .price(new BigDecimal("11.99"))
                    .build());

            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer(){
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My Beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .upc("564641415")
                        .price(new BigDecimal("11.99"))
                .build());

        //Dit de réécrire directment la database sinon ça va trop vite
        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}