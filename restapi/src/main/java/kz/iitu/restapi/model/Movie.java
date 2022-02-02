package kz.iitu.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class Movie {
    @Id
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq")
    private Integer id;

    @NotBlank(message = "Title must be inserted")
    String title;

    @NotBlank(message = "Category must be inserted")
    String category;

    @NotBlank(message = "Director must be inserted")
    String director;

    @NotBlank(message = "You must insert an IMDb profile")
    String imdb;

    @NotNull(message = "Release year must be inserted")
    @Min(value = 1900)
    Integer yearRelease;

    Integer rateMovie;

    @Column(nullable = true, length = 45)
    String image;

    @Transient
    public String getImagePath(){
        if(image == null || id == null)
            return null;
        return "/images/" + id+ "/"+ image;
    }
}
