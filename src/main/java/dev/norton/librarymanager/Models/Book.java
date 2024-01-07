package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String Title;
    private String Summary;
    private LocalDate PublishedIn;
    private int Pages;
    private int TotalCopys;

    // ------------------------------------------------------------------------------

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author Author;
    @ManyToOne
    @JoinColumn(name = "saga_id")
    private Saga Saga;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher Publisher;

    @ManyToMany
    @JoinTable(
            name = "book_gender",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id"))
    private List<Gender> Genders;

    // ------------------------------------------------------------------------------
    // Custom Methods

    public List<Gender> getGenders() {
        Hibernate.initialize(Genders);
        return Genders;
    }
}
