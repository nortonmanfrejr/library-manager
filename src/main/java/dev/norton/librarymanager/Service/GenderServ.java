package dev.norton.librarymanager.Service;

import dev.norton.librarymanager.Model.Gender;
import dev.norton.librarymanager.Repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderServ {

    @Autowired
    public GenderRepository Repo;

    // ------------------------------------------------------------------------------

    // region GET

    public List<Gender> FindAll() {
        return Repo.findAll();
    }

    public Optional<Gender> FindById(Long id) {
        return Repo.findById(id);
    }

    public Optional<Gender> FindByName(String name) {
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

    public Gender Create(Gender reg) {
        return Repo.save(reg);
    }
    public List<Gender> Create(Iterable<Gender> reg){
        return Repo.saveAll(reg);
    }

    // ------------------------------------------------------------------------------
    // UPDATE

    public void Edit(Gender reg) {
        Repo.save(reg);
    }

    public void Edit(Iterable<Gender> reg){
        Repo.saveAll(reg);
    }

    // endregion MODIFY

    // ------------------------------------------------------------------------------

    // region DELETE

    public void Delete(Long id) {
        Repo.deleteById(id);
    }

    public void Delete(String name) {
        Gender x = FindByName(name).orElse(new Gender());
        Repo.delete(x);
    }

    public void Delete(Gender reg) {
        Repo.delete(reg);
    }

    public void Delete(Iterable<Gender> reg) {
        Repo.deleteAll(reg);
    }
    // endregion DELETE
}
