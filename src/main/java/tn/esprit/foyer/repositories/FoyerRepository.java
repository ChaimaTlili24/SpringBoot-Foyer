package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.models.Foyer;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
    @Query("SELECT DISTINCT f FROM Foyer f JOIN f.blocs b JOIN b.chambres c WHERE c.numeroChambre IN :numerosChambres")
    List<Foyer> findFoyersByNumerosChambres(@Param("numerosChambres") List<Long> numerosChambres);
    @Query("SELECT DISTINCT f FROM Foyer f " +
            "JOIN f.blocs b " +
            "JOIN b.chambres c " +
            "WHERE c.numeroChambre IN :numerosChambres")
    List<Foyer> findDistinctByChambresNumeroChambreIn(@Param("numerosChambres") List<String> numerosChambres);
}
