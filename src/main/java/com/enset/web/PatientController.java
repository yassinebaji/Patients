package com.enset.web;

import com.enset.entities.Patient;
import com.enset.entities.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path="/index")
    public String listPatients(Model model , @RequestParam(name="motcle",defaultValue="")String mc,
                               @RequestParam(name="page",defaultValue="0")int page,
                               @RequestParam(name="size",defaultValue="5")int size
    ) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(mc, PageRequest.of(page,size));
        model.addAttribute("listepatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        model.addAttribute("motcle",mc);


        return "PatientsView";
    }

    @GetMapping(path="/deletPatient")
    public String deletePatient(Long id,String mc,int page,int size) {
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&size="+size+"&motcle=";

    }

    @GetMapping(path="/formPatient")
    public String FormPatient(Model model) {

        model.addAttribute("patient",new Patient());
        return "formPatient";

    }
    @PostMapping(path="/savePatient")
    public String savePatient(Model model,@Valid Patient patient2 , BindingResult bindingResult ) {
        if(bindingResult.hasErrors())
            return "formPatient";

        patientRepository.save(patient2);
        model.addAttribute("patient",patient2);

        return "Confirmation";

    }

    @GetMapping(path="/Editpatient")
    public String Editpatient(Model model,Long id) {
        Patient p = patientRepository.findById(id).get();
        System.out.println("date :"+p.getDateNaissance());
        model.addAttribute("patient",p);
        return "EditPatient";
    }

    @PostMapping(path="/saveEditPatient")
    public String saveEditPatient(@Valid Patient patient2 , BindingResult bindingResult ) {

        System.out.println(patient2.toString());
        if(bindingResult.hasErrors())
            return "EditPatient";

        patientRepository.save(patient2);
        return "redirect:/index";

    }

}
