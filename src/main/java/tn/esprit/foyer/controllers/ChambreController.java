package tn.esprit.foyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.foyer.models.Chambre;
import tn.esprit.foyer.models.TypeChambre;
import tn.esprit.foyer.repositories.ChambreRepository;
import tn.esprit.foyer.services.ChambreService;
import tn.esprit.foyer.services.ChambreServiceImpl;

import java.util.List;
@Tag(name = "Gestion Chambre")
@RestController
@RequestMapping("/chambre")

public class ChambreController {
    private final ChambreService chambreService;
    private final ChambreServiceImpl chambreServiceImpl;
    @Autowired
    private ChambreRepository chambreRepository;

    public ChambreController(ChambreService chambreService, ChambreServiceImpl chambreServiceImpl) {
        this.chambreService = chambreService;
        this.chambreServiceImpl = chambreServiceImpl;
    }

    @PostMapping("/ajouterChambre")
    public Chambre createChambre(@RequestBody Chambre chambre){
        return chambreService.saveChambre(chambre);
    }
    @DeleteMapping("/supprimerChambre/{IdChambre}")
    public void deleteChambre(@PathVariable Long IdChambre){
        chambreService.deleteChambre(IdChambre);
    }
    @GetMapping("/getById/{IdChambre}")
    public Chambre getChambreById(@PathVariable Long IdChambre){
        return chambreService.getChambreById(IdChambre);
    }
    @Operation(description = "récupérer toutes les chambres de la base de données")
    @GetMapping
    public List<Chambre> getAllChambres(){
        return chambreService.getAllChambres();
    }
    @PutMapping("/updateChambre/{IdChambre}")
    public Chambre updateChambre(@PathVariable Long IdChambre, @RequestBody Chambre chambre) {
        chambre.setIdChambre(IdChambre);
        return chambreService.saveChambre(chambre);
    }
    @GetMapping("/Type/{typeChambre}")
    public List<Chambre> getAllChambresByTypeChambre(@PathVariable TypeChambre typeChambre){
        return chambreServiceImpl.getChambreByTypeChambre(typeChambre);
    }
    @GetMapping("/Numero/{numeroChambre}")
    public Chambre getChambreByNumeroChambre(@PathVariable Long numeroChambre){
        return chambreServiceImpl.getChambreByNumeroChambre(numeroChambre);
    }
    @GetMapping("/bloc/{blocId}")
    public List<Chambre> getChambresParBloc(@PathVariable Long blocId) {
        return chambreServiceImpl.getChambresParBloc(blocId);
    }
    @GetMapping("/countNonValide")
    public Long countChambresNonValidesAnneePrecedente() {
        return chambreService.countChambresAvecReservationNonValideAnneePrecedente();
    }
    @GetMapping("/count-non-valides")
    public long getNombreChambresNonValides(@RequestParam String anneeUniversitaireCourante) {
        return chambreService.compterChambresReservationsNonValides(anneeUniversitaireCourante);
    }
    @PutMapping("/affecterChambreABloc")
    public Chambre affecterChambreABloc(@RequestParam Long num, @RequestParam Long idBloc) {
        return chambreService.affecterChambreABloc(num, idBloc);
    }
    @PutMapping("/desaffecterChambreDeBloc")
    public Chambre desaffecterChambreDeBloc(@RequestParam Long num, @RequestParam Long idBloc) {
        return chambreService.desaffecterChambreDeBloc(num, idBloc);
    }

}
