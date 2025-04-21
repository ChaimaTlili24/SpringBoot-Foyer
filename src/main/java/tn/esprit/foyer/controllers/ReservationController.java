package tn.esprit.foyer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.foyer.models.Reservation;
import tn.esprit.foyer.services.ReservationService;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping("/ajouterResrvationAvecEtudiants")
    public Reservation ajouteReservationAvecEtudiants(@RequestBody Reservation reservation) {
        return reservationService.ajouterReservationAvecEtudiants(reservation);
    }

}
