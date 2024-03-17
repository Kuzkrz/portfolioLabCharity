package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
@RequiredArgsConstructor
@Controller
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;


    @RequestMapping("/donation")
    public String showDonationForm(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("institutions", institutionService.getAllInstitutions());
        return "formSteps";
    }

    @PostMapping("/saveDonation")
    public String saveDonation(@ModelAttribute("donation") @Valid Donation donation,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("institutions", institutionService.getAllInstitutions());
            return "formSteps";
        }
        Institution institution = donation.getInstitution();
        if (institution != null && institution.getId() != null) {
            institution = institutionService.getInstitutionById(institution.getId());
            donation.setInstitution(institution);
            donationService.saveDonation(donation);
            return "redirect:/formConfirmation";
        } else {
            System.out.println("Nie ma takiej instytucji");
            // Obsłuż brak wybranej instytucji, na przykład wyświetl komunikat błędu
            return "redirect:/error"; // lub inna odpowiednia ścieżka
        }
    }
}

