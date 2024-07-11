package be.hanagami.spring6restmvc.controller;

import be.hanagami.spring6restmvc.entities.Beer;
import be.hanagami.spring6restmvc.mappers.BeerMapper;
import be.hanagami.spring6restmvc.model.BeerDTO;
import be.hanagami.spring6restmvc.repositories.BeerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BeerControllerIT {


    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerController beerController;

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void SetUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    void testPatchBeerBadName() throws Exception {
        Beer beer = beerRepository.findAll().get(0);

        Map<String, Object> beerMap= new HashMap<>();
        beerMap.put("beerName", "New NamezegfzerqgrgzrqNew NamezegfzerqgrgzrqNew NamezegfzerqgrgzrqNew NamezegfzerqgrgzrqNew NamezegfzerqgrgzrqNew NamezegfzerqgrgzrqNew Namezegfzerqgrgzrq");

        mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest());
    }

    @Rollback
    @Transactional
    @Test
    void testPatchExistingBeer(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "UPDATED";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = beerController.updateBeerPatchById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer updateBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updateBeer.getBeerName()).isEqualTo(beerName);
    }

    @Test
    void testDeleteByIdNotFound(){
        assertThrows(NotFoundException.class, () -> {
            beerController.deleteById(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void deletedById(){
        Beer beer = beerRepository.findAll().get(0);

        ResponseEntity responseEntity = beerController.deleteById(beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(beerRepository.findById(beer.getId()).isEmpty());

//        Beer foundBeer = beerRepository.findById(beer.getId()).get();
//        assertThat(foundBeer).isNull();
    }



    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundException.class, () -> {
            beerController.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void updateExistingBeer(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "UPDATED";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = beerController.updateById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer updateBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updateBeer.getBeerName()).isEqualTo(beerName);
    }

    @Rollback
    @Transactional
    @Test
    void saveNewBeerTest(){
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer")
                .build();

        ResponseEntity responseEntity = beerController.handlePost(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID saveUUID = UUID.fromString(locationUUID[4]);

        Beer beer = beerRepository.findById(saveUUID).get();
        assertThat(beer).isNotNull();
    }

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            beerController.getBeerById(UUID.randomUUID());
        });
    }

    @Test
    void testGetById() {
        Beer beer = beerRepository.findAll().get(0);

        BeerDTO dto = beerController.getBeerById(beer.getId());

        assertThat(dto).isNotNull();
    }

    @Test
    void testListBeer() {
        List<BeerDTO> dtos = beerController.listBeer();

        assertThat(dtos.size()).isEqualTo(3);
    }

    //Si on run les deux test en même temps, le deuxième ne passe pas car pas de rollback automatiquement
    @Rollback
    @Transactional
    @Test
    void testEmptyList(){
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeer();

        assertThat(dtos.size()).isEqualTo(0);

    }
}