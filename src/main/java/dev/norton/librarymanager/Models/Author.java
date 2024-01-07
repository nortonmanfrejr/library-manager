package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String Name;


    // ------------------------------------------------------------------------------
    // Unmapped fields

    @Transient
    private List<Book> Books;
    @Transient
    private List<Saga> Sagas;

}
