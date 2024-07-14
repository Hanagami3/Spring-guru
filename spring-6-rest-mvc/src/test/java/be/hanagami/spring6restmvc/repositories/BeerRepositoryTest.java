package be.hanagami.spring6restmvc.repositories;

import be.hanagami.spring6restmvc.bootstrap.BootstrapData;
import be.hanagami.spring6restmvc.entities.Beer;
import be.hanagami.spring6restmvc.model.BeerStyle;
import be.hanagami.spring6restmvc.service.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerIsLisByStyle(){
        List<Beer> list = beerRepository.findAllByBeerStyle(BeerStyle.IPA);

        assertThat(list.size()).isEqualTo(548);
    }

    @Test
    void testGetBeerIsListByName() {
        List<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");

        assertThat(list.size()).isEqualTo(336);
    }

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