package dev.norton.librarymanager.EpModels;

import dev.norton.librarymanager.Models.Gender;
import lombok.Data;

import java.util.List;

@Data
public class GenderEpMod {

    // ------------------------------------------------------------------------------
    // Generals

    public Gender SingleGender;
    public List<Gender> ListGender;

    // ------------------------------------------------------------------------------

}
