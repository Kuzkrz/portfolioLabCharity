package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
@RequiredArgsConstructor
@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public Institution saveInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    public Institution getInstitutionById(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    public void deleteInstitutionById(Long id) {
        institutionRepository.deleteById(id);
    }
}

