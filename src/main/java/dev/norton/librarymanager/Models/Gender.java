package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.List;

@Entity
@Table(name = "gender")
@Data
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String Name;
    private String Description;

    /**
     * Examples of books that fit into this genre.
     * */
    @ManyToMany(mappedBy = "Genders")
    private List<Book> Books;

    // ------------------------------------------------------------------------------
    // Custom Methods

    public List<Book> getExamples() {
        Hibernate.initialize(Books);
        return Books;
    }
}