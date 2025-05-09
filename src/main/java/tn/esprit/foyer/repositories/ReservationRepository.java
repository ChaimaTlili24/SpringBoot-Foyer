package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.models.Chambre;
import tn.esprit.foyer.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.chambre = :chambre AND FUNCTION('YEAR', r.anneeUniversitaire) = :year AND r.estValide = true")
    int countReservationsForChambreAndYear(@Param("chambre") Chambre chambre, @Param("year") int year);

}
