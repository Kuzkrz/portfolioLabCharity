package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DonationService {

    private final DonationRepository donationRepository;

    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    public void deleteDonationById(Long id) {
        donationRepository.deleteById(id);
    }

    public int calculateTotalBags() {
        return donationRepository.sumOfAllBags();
    }
    public long countTotalDonations() {
        return donationRepository.count();
    }
}
