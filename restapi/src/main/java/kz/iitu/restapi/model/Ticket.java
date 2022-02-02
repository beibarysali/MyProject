package kz.iitu.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

//ticket table
@Entity
@Table(name = "tickets")
@Getter@Setter
public class Ticket {
    @Id
    private Integer buyerId;//по этому id найдем email, firstName, lastName;
    private Integer movieId;//по этому id найдем movieTitle
    @Transient
    private boolean isActive;//активна ли билет
    private LocalDateTime dateTime;//время показа
//    private Integer seat;//место


}
