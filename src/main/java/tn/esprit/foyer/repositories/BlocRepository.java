package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.models.Bloc;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
    Bloc IdBloc(Long idBloc);
    @Query("SELECT b FROM Bloc b LEFT JOIN FETCH b.chambres")
    List<Bloc> findAllWithChambres();
}
