package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.List;

@Entity
@Table(name = "author")
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String Name;

    @OneToMany(mappedBy = "Author", fetch = FetchType.LAZY)
    private List<Book> Books;
    @OneToMany(mappedBy = "Author", fetch = FetchType.LAZY)
    private List<Saga> Sagas;

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
