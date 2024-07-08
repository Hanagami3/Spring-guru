package be.hanagami.spring6restmvc.mappers;

import be.hanagami.spring6restmvc.entities.Beer;
import be.hanagami.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDTOToBeer(BeerDTO dto);

    BeerDTO beerToBeerDTO(Beer beer);
}
