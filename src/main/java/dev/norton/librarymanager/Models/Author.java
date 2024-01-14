package dev.norton.librarymanager.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long ID;
    private String Name;

    @OneToMany(mappedBy = "Author", fetch = FetchType.LAZY)
    private List<Book> Books;
    @OneToMany(mappedBy = "Author", fetch = FetchType.LAZY)
    private List<Saga> Sagas;

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

    public List<Book> getBooks() {
        Hibernate.initialize(Books);
        return Books;
    }

    public List<Saga> getSagas() {
        Hibernate.initialize(Sagas);
        return Sagas;
    }
}
