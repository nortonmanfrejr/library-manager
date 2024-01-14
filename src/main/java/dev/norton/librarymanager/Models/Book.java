package dev.norton.librarymanager.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
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
    // Ignored Fields
    @JsonIgnore
    public LocalDate DateIncludedIn;
    @JsonIgnore
    public LocalTime TimeInclusionIn;

    @PrePersist
    public void DefFields() {
        DateIncludedIn = LocalDate.now();
        TimeInclusionIn = LocalTime.now();
    }

    // ------------------------------------------------------------------------------
    // Custom Methods

    public List<Gender> getGenders() {
        Hibernate.initialize(Genders);
        return Genders;
    }

    // ------------------------------------------------------------------------------



}
