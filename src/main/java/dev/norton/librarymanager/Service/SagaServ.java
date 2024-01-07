package dev.norton.librarymanager.Service;

import dev.norton.librarymanager.Model.Author;
import dev.norton.librarymanager.Model.Book;
import dev.norton.librarymanager.Model.Publisher;
import dev.norton.librarymanager.Model.Saga;
import dev.norton.librarymanager.Repository.SagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SagaServ {

    @Autowired
    public SagaRepository Repo;

    // ------------------------------------------------------------------------------

    // region GET

    public List<Saga> FindAll() {
        return Repo.findAll();
    }

    // ------------------------------------------------------------------------------

    public Optional<Saga> FindById(Long id) {
        return Repo.findById(id);
    }

    public Optional<Saga> FindByTitle(String title) {
        return Repo.findAll()
                .stream()
                .filter(x -> x.getTitle().equals(title) || x.getTitle().contains(title))
                .findFirst();
    }

    public Optional<Saga> FindByAuthor(Author reg) {
        return Repo.findAll()
                .stream()
                .filter(x -> x.getAuthor().equals(reg))
                .findFirst();
    }

    public Optional<Saga> FindByPublisher(Publisher reg) {
        return Repo.findAll()
                .stream()
                .filter(x -> x.getPublisher().equals(reg))
                .findAny();
    }

    public Optional<Saga> FindByBook(Book reg) {
        return Repo.findAll()
                .stream()
                .filter(x -> x.getBooks().contains(reg))
                .findAny();
    }

    // ------------------------------------------------------------------------------

    public Boolean Exists(Long id) {
        return FindById(id).isPresent();
    }

    public Boolean Exists(String title) {
        return FindByTitle(title).isPresent();
    }

    public Boolean Exists(Author reg) {
        return FindByAuthor(reg).isPresent();
    }

    public Boolean Exists(Publisher reg) {
        return FindByPublisher(reg).isPresent();
    }

    public Boolean Exists(Book reg) {
        return FindByBook(reg).isPresent();
    }

    // endregion GET

    // ------------------------------------------------------------------------------

    // region MODIFY
    // CREATE

    public Saga Create(Saga reg) {
        return Repo.save(reg);
    }
    public List<Saga> Create(Iterable<Saga> reg){
        return Repo.saveAll(reg);
    }

    // ------------------------------------------------------------------------------
    // UPDATE

    public void Edit(Saga reg) {
        Repo.save(reg);
    }

    public void Edit(Iterable<Saga> reg){
        Repo.saveAll(reg);
    }

    // endregion MODIFY

    // ------------------------------------------------------------------------------

    // region DELETE

    public void Delete(Long id) {
        Repo.deleteById(id);
    }

    public void Delete(String title) {
        Saga x = FindByTitle(title).orElse(new Saga());
        Repo.delete(x);
    }

    public void Delete(Saga reg) {
        Repo.delete(reg);
    }

    public void Delete(Iterable<Saga> reg) {
        Repo.deleteAll(reg);
    }
    // endregion DELETE
}
