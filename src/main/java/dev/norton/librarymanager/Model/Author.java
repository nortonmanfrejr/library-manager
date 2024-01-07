package dev.norton.librarymanager.Model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.util.List;

@Entity
@Table(name = "author")
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

    // ------------------------------------------------------------------------------
    // Default Methods

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
