package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "saga")
@Data
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    // Custom Methods

    public List<Book> getBooks() {
        Hibernate.initialize(Books);
        return Books;
    }
}
