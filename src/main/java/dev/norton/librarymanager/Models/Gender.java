package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String Name;
    private String Description;

    // ------------------------------------------------------------------------------
    // Unmapped fields
    /**
     * Examples of books that fit into this genre.
     * */
    @Transient
    private List<Book> Examples;

}