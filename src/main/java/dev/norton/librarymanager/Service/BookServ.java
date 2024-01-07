package dev.norton.librarymanager.Service;

import dev.norton.librarymanager.Model.Book;
import dev.norton.librarymanager.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServ {

    @Autowired
    public BookRepository Repo;

    // ------------------------------------------------------------------------------

    // region GET

    public List<Book> FindAll() {
        return Repo.findAll();
    }

    public Optional<Book> FindById(Long id) {
        return Repo.findById(id);
    }

    public Optional<Book> FindByTitle(String title) {
        return Repo.findAll()
                .stream()
                .filter(x -> x.getTitle().equals(title) || x.getTitle().contains(title))
                .findFirst();
    }

    public Boolean Exists(Long id) {
        return FindById(id).isPresent();
    }

    public Boolean Exists(String title) {
        return FindByTitle(title).isPresent();
    }

    // endregion GET

    // ------------------------------------------------------------------------------

    // region MODIFY
    // CREATE

    public Book Create(Book reg) {
        return Repo.save(reg);
    }
    public List<Book> Create(Iterable<Book> reg){
        return Repo.saveAll(reg);
    }

    // ------------------------------------------------------------------------------
    // UPDATE

    public void Edit(Book reg) {
        Repo.save(reg);
    }

    public void Edit(Iterable<Book> reg){
        Repo.saveAll(reg);
    }

    // endregion MODIFY

    // ------------------------------------------------------------------------------

    // region DELETE

    public void Delete(Long id) {
        Repo.deleteById(id);
    }

    public void Delete(String title) {
        Book x = FindByTitle(title).orElse(new Book());
        Repo.delete(x);
    }

    public void Delete(Book reg) {
        Repo.delete(reg);
    }

    public void Delete(Iterable<Book> reg) {
        Repo.deleteAll(reg);
    }
    // endregion DELETE
}
