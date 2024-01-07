package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
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

    private Author Author;


    // ------------------------------------------------------------------------------
    // Unmapped fields

    @Transient
    private List<Book> Examples;

}
