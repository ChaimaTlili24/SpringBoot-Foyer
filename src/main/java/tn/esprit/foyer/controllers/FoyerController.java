package tn.esprit.foyer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyer.models.Foyer;
import tn.esprit.foyer.repositories.FoyerRepository;
import tn.esprit.foyer.services.FoyerService;
import tn.esprit.foyer.services.FoyerServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/foyers")
public class FoyerController {
    private final FoyerService foyerService;
    private final FoyerServiceImpl foyerServiceImpl;
    @Autowired
    private FoyerRepository foyerRepository;

    public FoyerController(FoyerService foyerService , FoyerServiceImpl foyerServiceImpl) {
        this.foyerService = foyerService;
        this.foyerServiceImpl = foyerServiceImpl;
    }

    @GetMapping("/FoyerByChambres")
    public List<Foyer> getFoyersByNumerosChambres(@RequestParam List<Long> numerosChambres) {
        return foyerService.getFoyersByNumerosChambres(numerosChambres);
    }
    @GetMapping("/chambres")
    public ResponseEntity<List<Foyer>> getFoyersByChambres(@RequestParam List<String> numerosChambres) {
        System.out.println("Paramètres reçus : " + numerosChambres);

        List<Foyer> foyers = foyerRepository.findDistinctByChambresNumeroChambreIn(numerosChambres);
        System.out.println("Foyers trouvés : " + foyers.size());

        if (foyers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foyers);
    }
    @PostMapping("/ajouterFoyerAvecBlocs")
    public Foyer ajouterFoyerAvecBlocs(@RequestBody Foyer foyer) {
        return foyerService.ajouterFoyerAvecBlocsAssociés(foyer);
    }

}
