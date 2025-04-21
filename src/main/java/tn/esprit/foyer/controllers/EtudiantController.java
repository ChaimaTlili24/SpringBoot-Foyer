package tn.esprit.foyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.foyer.models.Etudiant;
import tn.esprit.foyer.repositories.EtudiantRepository;
import tn.esprit.foyer.services.EtudiantService;
import tn.esprit.foyer.services.EtudiantServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    private final EtudiantService etudiantService;
    private final EtudiantServiceImpl etudiantServiceImpl;
    @Autowired
    private final EtudiantRepository etudiantRepository;

    public EtudiantController(EtudiantService etudiantService , EtudiantServiceImpl etudiantServiceImpl, EtudiantRepository etudiantRepository, EtudiantRepository etudiantRepository1) {
        this.etudiantService = etudiantService;
        this.etudiantServiceImpl = etudiantServiceImpl;
        this.etudiantRepository = etudiantRepository1;
    }

    @GetMapping("/NomEtudantsAvecReservationValide")
    public List<String> getNomsEtudiantsAvecReservationValide() {
        return etudiantService.getNomsEtudiantsAvecReservationValideAnneeCourante();
    }
    @GetMapping("/avec-reservation-valide")
    public List<String> getEtudiantsAvecReservationValide(@RequestParam Date anneeUniversitaire) {
        return etudiantRepository
                .findByReservationsEstValideIsTrueAndReservationsAnneeUniversitaire(anneeUniversitaire)
                .stream()
                .map(Etudiant::getNomEt)
                .collect(Collectors.toList());
    }

}
