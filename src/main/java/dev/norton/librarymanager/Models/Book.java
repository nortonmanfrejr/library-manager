package dev.norton.librarymanager.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String Title;
    private String Summary;
    private LocalDate PublishedIn;
    private int Pages;
    private int TotalCopys;


    // ------------------------------------------------------------------------------

    private Author Author;
    private Saga Saga;
    private Publisher Publisher;


    // ------------------------------------------------------------------------------
    // Unmapped fields

    @Transient
    private List<Gender> Genders;

}
