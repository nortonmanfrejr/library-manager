package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
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
    // Unmapped fields

    @Transient
    List<Book> PublishedBooks;

    @Transient
    List<Saga> PublishedSaga;

}
