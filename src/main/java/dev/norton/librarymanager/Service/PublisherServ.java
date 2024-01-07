package dev.norton.librarymanager.Service;

import dev.norton.librarymanager.Model.Publisher;
import dev.norton.librarymanager.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServ {

    @Autowired
    public PublisherRepository Repo;

    // ------------------------------------------------------------------------------

    // region GET

    public List<Publisher> FindAll() {
        return Repo.findAll();
    }

    public Optional<Publisher> FindById(Long id) {
        return Repo.findById(id);
    }

    public Optional<Publisher> FindByName(String name) {
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

    public Publisher Create(Publisher reg) {
        return Repo.save(reg);
    }
    public List<Publisher> Create(Iterable<Publisher> reg){
        return Repo.saveAll(reg);
    }

    // ------------------------------------------------------------------------------
    // UPDATE

    public void Edit(Publisher reg) {
        Repo.save(reg);
    }

    public void Edit(Iterable<Publisher> reg){
        Repo.saveAll(reg);
    }

    // endregion MODIFY

    // ------------------------------------------------------------------------------

    // region DELETE

    public void Delete(Long id) {
        Repo.deleteById(id);
    }

    public void Delete(String name) {
        Publisher x = FindByName(name).orElse(new Publisher());
        Repo.delete(x);
    }

    public void Delete(Publisher reg) {
        Repo.delete(reg);
    }

    public void Delete(Iterable<Publisher> reg) {
        Repo.deleteAll(reg);
    }
    // endregion DELETE
}
