package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.models.Etudiant;

import java.util.Date;
import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    @Query("SELECT DISTINCT e.nomEt FROM Etudiant e JOIN e.reservations r WHERE r.estValide = true AND YEAR(r.anneeUniversitaire) = YEAR(CURRENT_DATE)")
    List<String> findNomsEtudiantsAvecReservationValideAnneeCourante();
    List<Etudiant> findByReservationsEstValideIsTrueAndReservationsAnneeUniversitaire(Date anneeUniversitaire);
}
