package tn.esprit.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.models.Chambre;
import tn.esprit.foyer.models.Reservation;
import tn.esprit.foyer.repositories.ChambreRepository;
import tn.esprit.foyer.repositories.ReservationRepository;

import java.time.Year;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final ChambreRepository chambreRepository;
    @Override
    public Reservation ajouterReservationAvecEtudiants(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    @Scheduled(fixedRate = 300000) // 5 minutes = 300000 ms
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        List<Chambre> chambres = chambreRepository.findAllChambres();
        int currentYear = Year.now().getValue();

        for (Chambre chambre : chambres) {
            int totalPlaces = 1; // à adapter si chaque chambre a plus d’une place
            int reservedPlaces = reservationRepository.countReservationsForChambreAndYear(chambre, currentYear);

            int available = totalPlaces - reservedPlaces;

            if (available <= 0) {
                System.out.println("La chambre " + chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est complète");
            } else {
                System.out.println("Le nombre de place disponible pour la chambre " + chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est " + available);
            }
        }
    }
}
