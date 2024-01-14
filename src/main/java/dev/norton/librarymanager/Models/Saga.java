package dev.norton.librarymanager.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "saga")
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long ID;
    private String Title;
    private String Summary;

    private String Status;
    private LocalDate Init;
    private LocalDate End;

    // ------------------------------------------------------------------------------

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author Author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher Publisher;

    @OneToMany(mappedBy = "Saga", fetch = FetchType.LAZY)
    private List<Book> Books;

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
}
