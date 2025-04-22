package tn.esprit.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.models.Bloc;
import tn.esprit.foyer.models.Chambre;
import tn.esprit.foyer.models.TypeChambre;
import tn.esprit.foyer.repositories.BlocRepository;
import tn.esprit.foyer.repositories.ChambreRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class ChambreServiceImpl implements ChambreService {
    // type 1
    //private final ChambreRepository chambreRepository;

    //public ChambreServiceImpl(ChambreRepository chambreRepository) {
      //  this.chambreRepository = chambreRepository;
    //}
    @Autowired
    private ChambreRepository chambreRepository;
    @Override
    public Chambre saveChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(Long IdChambre) {
        chambreRepository.deleteById(IdChambre);
    }

    @Override
    public Chambre getChambreById(Long IdChambre) {
        return null;
    }

    @Override
    public List<Chambre> getAllChambres() {
        return List.of();
    }

    public List<Chambre> getChambreByTypeChambre(TypeChambre typeChambre){
        return chambreRepository.findAllByTypeC(typeChambre);
    }
    public Chambre getChambreByNumeroChambre(Long numeroChambre){
        return chambreRepository.findByNumeroChambre(numeroChambre);
    }
    @Autowired
    private BlocRepository blocRepository;

    public List<Chambre> getChambresParBloc(Long blocId) {
        Optional<Bloc> bloc = blocRepository.findById(blocId); // Utilisation de l'instance injectée
        if (bloc.isPresent()) {
            return chambreRepository.findByBloc(bloc.get());
        }
        return List.of(); // Retourne une liste vide si le bloc n'existe pas
    }
    @Override
    public Long countChambresAvecReservationNonValideAnneePrecedente() {
        return chambreRepository.countChambresAvecReservationNonValideAnneePrecedente();
    }
    @Override
    public long compterChambresReservationsNonValides(String anneeUniversitaire) {
        return chambreRepository.countChambresAvecReservationsNonValidesAvantAnnee(anneeUniversitaire);
    }

    @Override
    public Chambre affecterChambreABloc(Long num, Long idBloc) {
        // 1. Trouver la chambre par son numéro
        Chambre chambre = chambreRepository.findByNumeroChambre(num);
        if (chambre == null) {
            return null; // Chambre non trouvée
        }

        // 2. Trouver le bloc cible
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        if (bloc == null) {
            return null; // Bloc non trouvé
        }

        // 3. Retirer la chambre de son ancien bloc (si elle en a un)
        if (chambre.getBloc() != null) {
            chambre.getBloc().getChambres().remove(chambre);
        }

        // 4. Affecter la chambre au nouveau bloc
        chambre.setBloc(bloc);
        bloc.getChambres().add(chambre);

        // 5. Sauvegarder
        chambreRepository.save(chambre);
        blocRepository.save(bloc);

        return chambre;
    }


    @Override
    public Chambre desaffecterChambreDeBloc(Long num, Long idBloc) {
        // Recherche de la chambre par son numéro directement
        Chambre chambre = chambreRepository.findByNumeroChambre(num);

        if (chambre != null && chambre.getBloc() != null && chambre.getBloc().getIdBloc().equals(idBloc)) {
            Bloc bloc = chambre.getBloc();

            // Désaffecter la chambre du bloc
            bloc.getChambres().remove(chambre);
            chambre.setBloc(null);

            // Sauvegarder les modifications
            chambreRepository.save(chambre);
            blocRepository.save(bloc);

            return chambre;
        }
        return null;
    }
//    @Scheduled(fixedDelay = 10000)
//    public void fixedDelayMethod() {
//        log.info("Method with fixed delay");
//    }
//    @Scheduled(fixedRate = 10000)
//    public void fixedRateMethod() {
//        log.info("Method with fixed Rate");
//}
//@Scheduled(cron = "0 * * * * *")
//public void listeChambre(){
//    List<Bloc> blocs = blocRepository.findAllWithChambres();
//
//    for (Bloc bloc : blocs) {
//        log.info("Bloc => " + bloc.getNomBloc() + " ayant une capacité " + bloc.getCapaciteBloc());
//
//        List<Chambre> chambres = bloc.getChambres();
//        if (chambres == null || chambres.isEmpty()) {
//            log.info("Pas de chambre disponible dans ce bloc");
//            log.info("***********************");
//        } else {
//            log.info("La liste des chambres pour ce bloc:");
//            for (Chambre chambre : chambres) {
//                log.info("NumChambre: " + chambre.getNumeroChambre() + " type: " + chambre.getTypeC());
//            }
//            log.info("***********************");
//        }
//    }
//}
@Scheduled(cron = "0 */5 * * * *") // Toutes les 5 minutes
public void pourcentageChambreParTypeChambre() {
    long totalChambres = chambreRepository.count();
    log.info("Nombre total des chambres: " + totalChambres);

    for (TypeChambre type : TypeChambre.values()) {
        long countType = chambreRepository.countByTypeC(type);
        double pourcentage = (totalChambres == 0) ? 0.0 : (countType * 100.0) / totalChambres;

        log.info("Le pourcentage des chambres pour le type " + type + " est égale à " + String.format("%.1f", pourcentage));
    }
}
} // Clôture