package dev.norton.librarymanager.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.util.List;

@Entity
@Table(name = "gender")
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}