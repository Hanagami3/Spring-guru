package be.hanagami.spring6restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Collate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

//@Data
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    //@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @UuidGenerator
    //Sans cela, Hibernate tente de mettre un binary dans un string et MySql n'aime pas ça
    //Donc demande de convertir le UUID en un String
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;
    private String name;

    //en gris car c'est la valeur par défault donc on ne spécifie rien
    @Column(length = 255)
    private String email;

    @Version
    private Integer  version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
