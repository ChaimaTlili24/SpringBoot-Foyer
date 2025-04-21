package tn.esprit.foyer.services;

import tn.esprit.foyer.models.Chambre;

import java.util.List;

public interface ChambreService {
    Chambre saveChambre(Chambre chambre);
    void deleteChambre(Long IdChambre);
    Chambre getChambreById(Long IdChambre);
    List<Chambre> getAllChambres();
    Long countChambresAvecReservationNonValideAnneePrecedente();

    long compterChambresReservationsNonValides(String anneeUniversitaire);
    Chambre affecterChambreABloc(Long num, Long idBloc);
    Chambre desaffecterChambreDeBloc(Long num, Long idBloc);
}
