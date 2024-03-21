package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    @GetMapping("/donation")
    public String showDonationForm(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryService.getAllCategories());
        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);
        return "formSteps";
    }

    @PostMapping("/saveDonation")
    public String saveDonation(@ModelAttribute("donation") @Valid Donation donation,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "formSteps";
        }

        Long institutionId = donation.getInstitution().getId();
        donation.setInstitution(institutionService.getInstitutionById(institutionId));

        donationService.saveDonation(donation);
        return "redirect:/formConfirmation";
    }
}



