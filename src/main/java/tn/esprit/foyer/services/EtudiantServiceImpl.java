package tn.esprit.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.repositories.EtudiantRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class EtudiantServiceImpl implements EtudiantService {
    @Autowired
    private final EtudiantRepository etudiantRepository;

    @Override
    public List<String> getNomsEtudiantsAvecReservationValideAnneeCourante() {
        return etudiantRepository.findNomsEtudiantsAvecReservationValideAnneeCourante();
    }
}
