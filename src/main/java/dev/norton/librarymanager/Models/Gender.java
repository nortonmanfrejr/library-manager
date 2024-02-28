package dev.norton.librarymanager.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.norton.librarymanager.Tools.MsgT;
import dev.norton.librarymanager.Tools.StrT;
import dev.norton.librarymanager.Tools.SysT;
import jakarta.persistence.*;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long ID;

    @Column(nullable = false, length = 50)
    public String Name;
    public String Description;

    // ------------------------------------------------------------------------------
    // Ignored Fields
    @JsonIgnore
    public LocalDate DateIncludedIn;
    @JsonIgnore
    public LocalTime TimeInclusionIn;

    @PrePersist
    public void DefFields() {
        DateIncludedIn = LocalDate.now();
        TimeInclusionIn = LocalTime.now();
    }

    /**
     * Examples of books that fit into this genre.
     * */
    @ManyToMany(mappedBy = "Genders")
    private List<Book> Books;

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

    // ------------------------------------------------------------------------------
    // Custom Methods

    public List<Book> getExamples() {
        Hibernate.initialize(Books);
        return Books;
    }

    // ------------------------------------------------------------------------------
    // Default ResponseEntity.
    /**
     * @param httpStat : Http status that will be returned if the request is successful.
     * @param msg      : Message that will be displayed when the request is returned.
     */

    private ResponseEntity<String> DefResponse(HttpStatus httpStat, String msg){
        return ResponseEntity
                .status(httpStat)
                .body(String
                        .format(msg,
                                SysT.Routine.GetVal(SysT.Routine.Gender),
                                StrT.SingleQuotStr(
                                        StrT.IsNullOrEmpty(Name)
                                                ? StrT.Empty
                                                : Name
                                )
                        )
                );
    }

    private ResponseEntity<String> DefResponse(String msg, String name){
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(String
                        .format(msg,
                                StrT.SingleQuotStr(name)
                        )
                );
    }

    public ResponseEntity<String> DefSuccessfulResponse(){
        return DefResponse(HttpStatus.CREATED
                , MsgT.SuccessPost
        );
    }

    public ResponseEntity<String> DefConflictResponse(){
        return DefResponse(HttpStatus.CONFLICT
                , MsgT.DuplicatedPost
        );
    }

    public ResponseEntity<String> DefRequiredResponse(String FieldName){
        return DefResponse(
                MsgT.Required
                , FieldName
        );
    }
    // ------------------------------------------------------------------------------
}