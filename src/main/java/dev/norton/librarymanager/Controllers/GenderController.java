package dev.norton.librarymanager.Controllers;

import dev.norton.librarymanager.EpModels.GenderEpMod;
import dev.norton.librarymanager.Models.Gender;
import dev.norton.librarymanager.Service.GenderServ;
import dev.norton.librarymanager.Tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(value = "/gender")
@CrossOrigin(value = "*")
public class GenderController {

    @Autowired
    private GenderServ GenderServ;

    // region GET

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Gender> GetAll(){
        return GenderServ.GetAll();
    }

    // ------------------------------------------------------------------------------

    @GetMapping(value = "/name={name}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Gender> GetByName(@PathVariable String name){
        return GenderServ.GetByContainsEqualsName(name);
    }

    // endregion GET

    // ------------------------------------------------------------------------------

    // region POST/PUT

    private List<ResponseEntity<String>> RequiredResponse(Gender reg){
        List<ResponseEntity<String>> response = new ArrayList<>();

        if (StrT.IsNullOrEmpty(reg.Name)) response.add(reg.DefRequiredResponse("Name"));
        if (StrT.IsNullOrEmpty(reg.Description)) response.add(reg.DefRequiredResponse("Description"));

        return response;
    }

    @PostMapping(value = "/Create")
    public List<ResponseEntity<String>> BasePost(@RequestBody GenderEpMod reg){
        boolean IsSinglePost = ObjT.IsNull(reg.SingleGender);
        return IsSinglePost ? BaseListPost(reg.ListGender) : BaseSinglePost(reg.SingleGender);
    }
    private List<ResponseEntity<String>> BaseSinglePost(@RequestBody Gender reg){

        // ------------------------------------------------------------------------------
        // Required

        List<ResponseEntity<String>> ReqResponse = RequiredResponse(reg);
        if (!ReqResponse.isEmpty()) return ReqResponse;

        // ------------------------------------------------------------------------------
        // Validation

        boolean Exists = GenderServ.Exists(reg.Name);
        List<ResponseEntity<String>> response = new ArrayList<>();
        response.add(Exists ? reg.DefConflictResponse() : reg.DefSuccessfulResponse());
        if (Exists) return response;

        // ------------------------------------------------------------------------------

        GenderServ.Create(reg);
        return response;
    }

    private List<ResponseEntity<String>> BaseListPost(@RequestBody List<Gender> regs){

        // ------------------------------------------------------------------------------
        // Validation

        List<ResponseEntity<String>> response = new ArrayList<>();
        List<Gender> ForRemove = new ArrayList<>();
        regs.forEach(x -> {

            List<ResponseEntity<String>> ReqResponse = RequiredResponse(x);
            boolean IsInvalidReg = !ReqResponse.isEmpty();
            if (IsInvalidReg) {
                ForRemove.add(x);
                response.addAll(ReqResponse);
            }

            boolean Exists = GenderServ.Exists(x.Name);
            if (Exists) ForRemove.remove(x);

            if (!IsInvalidReg) response.add(!Exists
                    ? x.DefSuccessfulResponse()
                    : x.DefConflictResponse()
            );
        });

        boolean ThisRemove = !ForRemove.isEmpty();
        if (ThisRemove) regs.removeAll(ForRemove);

        // ------------------------------------------------------------------------------

        GenderServ.Create(regs);
        return response;
    }

    // endregion POST/PUT

    // ------------------------------------------------------------------------------

}
