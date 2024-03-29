package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    @GetMapping("/")
    public String homeAction(Model model){

        List<Institution> allInstitutions = institutionService.getAllInstitutions();

        if (allInstitutions.isEmpty()) {
            model.addAttribute("noInstitutions", true);
            return "index";
        }

        List<Institution>randomInstitutions = getRandomInstitutions(allInstitutions);
        model.addAttribute("institutions",randomInstitutions);

        int totalBags = donationService.calculateTotalBags();
        model.addAttribute("totalBags", totalBags);

        long totalDonations = donationService.countTotalDonations();
        model.addAttribute("totalDonations",totalDonations);
        return "index";

    }
    private List<Institution> getRandomInstitutions(List<Institution> allInstitutions) {
        Random random = new Random();
        int totalSize = allInstitutions.size();
        boolean[] chosen = new boolean[totalSize];
        List<Institution> randomInstitutions = new ArrayList<>();

        int count = Math.min(4, totalSize); // Wybieramy minimum z czterech instytucji lub liczby dostępnych instytucji

        for (int i = 0; i < count; i++) {
            int index;
            do {
                index = random.nextInt(totalSize);
            } while (chosen[index]);
            chosen[index] = true;
            randomInstitutions.add(allInstitutions.get(index));
        }

        return randomInstitutions;
    }


}
