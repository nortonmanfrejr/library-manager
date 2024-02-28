package dev.norton.librarymanager.Service;

import dev.norton.librarymanager.Models.Gender;
import dev.norton.librarymanager.Repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderServ {

    @Autowired
    private GenderRepository repo;

    // region GET

    public List<Gender> GetAll(){
        return repo.findAll();
    }

    // ------------------------------------------------------------------------------

    public Optional<Gender> GetById(Long id){
        return GetAll()
                .stream()
                .filter(x -> x.getID().equals(id))
                .findFirst();
    }

    public boolean Exists(Long id){
        return GetById(id).isPresent();
    }

    // ------------------------------------------------------------------------------

    public List<Gender> GetByContainsEqualsName(String name){
        return GetAll()
                .stream()
                .filter(x -> x.getName().equals(name) || x.getName().contains(name))
                .toList();
    }

    public List<Gender> GetByEqualsName(String name){
        return GetAll()
                .stream()
                .filter(x -> x.getName().equals(name))
                .toList();
    }

    public boolean Exists(String name){
        return !GetByEqualsName(name).isEmpty();
    }
    // endregion GET

    // ------------------------------------------------------------------------------

    // region MODIFY

    public void Create(Gender reg){
        repo.save(reg);
    }

    public void Create(List<Gender> regs){
        repo.saveAll(regs);
    }

    // ------------------------------------------------------------------------------

    public void Edit(Gender reg){
        repo.save(reg);
    }

    public void Edit(List<Gender> regs){
        repo.saveAll(regs);
    }

    // endregion MODIFY

    // ------------------------------------------------------------------------------
}
