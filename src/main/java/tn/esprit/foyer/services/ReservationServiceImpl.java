package tn.esprit.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.models.Reservation;
import tn.esprit.foyer.repositories.ReservationRepository;
@Service
@AllArgsConstructor

public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Override
    public Reservation ajouterReservationAvecEtudiants(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
