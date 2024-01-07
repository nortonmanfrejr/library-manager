package dev.norton.librarymanager.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "saga")
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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author Author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher Publisher;

    @OneToMany(mappedBy = "Saga", fetch = FetchType.LAZY)
    private List<Book> Books;

    // ------------------------------------------------------------------------------
    // Custom Methods

    public List<Book> getBooks() {
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public LocalDate getInit() {
        return Init;
    }

    public void setInit(LocalDate init) {
        Init = init;
    }

    public LocalDate getEnd() {
        return End;
    }

    public void setEnd(LocalDate end) {
        End = end;
    }

    public Author getAuthor() {
        return Author;
    }

    public void setAuthor(Author author) {
        Author = author;
    }

    public Publisher getPublisher() {
        return Publisher;
    }

    public void setPublisher(Publisher publisher) {
        Publisher = publisher;
    }
}
