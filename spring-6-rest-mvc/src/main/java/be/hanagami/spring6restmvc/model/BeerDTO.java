package be.hanagami.spring6restmvc.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


//compile dans Lifecycle avec Maven + vérifier dans target
@Builder
//aller dans refractor puis delombok et cela va gérérer tout le code dans la classe
@Data
public class BeerDTO {
    private UUID id;
    private Integer version;

    //si string avec espace blanc
    @NotBlank
    @NotNull
    private String beerName;

    @NotNull
    private BeerStyle beerStyle;

    @NotBlank
    @NotNull
    private String upc;
    private Integer quantityOnHand;

    @NotNull
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}

