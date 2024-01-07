package dev.norton.librarymanager.Model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author Author;
    @ManyToOne
    @JoinColumn(name = "saga_id")
    private Saga Saga;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher Publisher;

    @ManyToMany
    @JoinTable(
            name = "book_gender",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id"))
    private List<Gender> Genders;

    // ------------------------------------------------------------------------------
    // Custom Methods

    public List<Gender> getGenders() {
        Hibernate.initialize(Genders);
        return Genders;
    }

    // ------------------------------------------------------------------------------
    // Default Methods

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public LocalDate getPublishedIn() {
        return PublishedIn;
    }

    public void setPublishedIn(LocalDate publishedIn) {
        PublishedIn = publishedIn;
    }

    public int getPages() {
        return Pages;
    }

    public void setPages(int pages) {
        Pages = pages;
    }

    public int getTotalCopys() {
        return TotalCopys;
    }

    public void setTotalCopys(int totalCopys) {
        TotalCopys = totalCopys;
    }

    public dev.norton.librarymanager.Model.Author getAuthor() {
        return Author;
    }

    public void setAuthor(dev.norton.librarymanager.Model.Author author) {
        Author = author;
    }

    public dev.norton.librarymanager.Model.Saga getSaga() {
        return Saga;
    }

    public void setSaga(dev.norton.librarymanager.Model.Saga saga) {
        Saga = saga;
    }
<<<<<<< HEAD

    public dev.norton.librarymanager.Model.Publisher getPublisher() {
        return Publisher;
    }

    public void setPublisher(dev.norton.librarymanager.Model.Publisher publisher) {
        Publisher = publisher;
    }
=======
>>>>>>> master
}
