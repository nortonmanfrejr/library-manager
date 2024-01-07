package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "publisher")
@Data
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private LocalDate Fundation;
    private String Name;
    private String Country;

    // ------------------------------------------------------------------------------
    // Contact

    // ------------------------------------------------------------------------------

    @OneToMany(mappedBy = "Publisher", fetch = FetchType.LAZY)
    private List<Book> Books;

    @OneToMany(mappedBy = "Publisher", fetch = FetchType.LAZY)
    private List<Saga> Sagas;

    // ------------------------------------------------------------------------------
    // Custom Methods

    public List<Book> getBooks() {
        Hibernate.initialize(Books);
        return Books;
    }
    public List<Saga> getPublishedSaga() {
        Hibernate.initialize(Sagas);
        return Sagas;
    }

}
