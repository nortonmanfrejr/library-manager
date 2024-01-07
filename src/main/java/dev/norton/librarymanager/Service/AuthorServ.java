package dev.norton.librarymanager.Service;

import dev.norton.librarymanager.Model.Author;
import dev.norton.librarymanager.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServ {

    @Autowired
    public AuthorRepository Repo;

    // ------------------------------------------------------------------------------

    // region GET

    public List<Author> FindAll() {
        return Repo.findAll();
    }

    public Optional<Author> FindById(Long id) {
        return Repo.findById(id);
    }

    public Optional<Author> FindByName(String name) {
        return Repo.findAll()
                .stream()
                .filter(x -> x.getName().equals(name) || x.getName().contains(name))
                .findFirst();
    }

    public Boolean Exists(Long id) {
        return FindById(id).isPresent();
    }

    public Boolean Exists(String name) {
        return FindByName(name).isPresent();
    }

    // endregion GET

    // ------------------------------------------------------------------------------

    // region MODIFY
    // CREATE

    public Author Create(Author reg) {
        return Repo.save(reg);
    }
    public List<Author> Create(Iterable<Author> reg){
        return Repo.saveAll(reg);
    }

    // ------------------------------------------------------------------------------
    // UPDATE

    public void Edit(Author reg) {
        Repo.save(reg);
    }

    public void Edit(Iterable<Author> reg){
        Repo.saveAll(reg);
    }

    // endregion MODIFY

    // ------------------------------------------------------------------------------

    // region DELETE

    public void Delete(Long id) {
        Repo.deleteById(id);
    }

    public void Delete(String name) {
        Author x = FindByName(name).orElse(new Author());
        Repo.delete(x);
    }

    public void Delete(Author reg) {
        Repo.delete(reg);
    }

    public void Delete(Iterable<Author> reg) {
        Repo.deleteAll(reg);
    }
    // endregion DELETE
}