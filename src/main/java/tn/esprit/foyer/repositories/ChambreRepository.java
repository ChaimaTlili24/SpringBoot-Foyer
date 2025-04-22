package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.models.Bloc;
import tn.esprit.foyer.models.Chambre;
import tn.esprit.foyer.models.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findAllByTypeC(TypeChambre typeChambre);

    Chambre findByNumeroChambre(Long numeroChambre);

    List<Chambre> findByBloc(Bloc bloc);
    @Query("SELECT COUNT(DISTINCT c) FROM Chambre c JOIN c.reservations r WHERE r.estValide = false AND YEAR(r.anneeUniversitaire) < YEAR(CURRENT_DATE)")
    Long countChambresAvecReservationNonValideAnneePrecedente();
    @Query("SELECT COUNT(c) FROM Chambre c JOIN c.reservations r " +
            "WHERE r.estValide = false AND r.anneeUniversitaire < :annee")
    long countChambresAvecReservationsNonValidesAvantAnnee(@Param("annee") String anneeUniversitaire);
    long countByTypeC(TypeChambre typeChambre);
    @Query("SELECT c FROM Chambre c")
    List<Chambre> findAllChambres();




}
