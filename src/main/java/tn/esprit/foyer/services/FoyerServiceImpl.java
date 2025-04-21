package tn.esprit.foyer.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.models.Bloc;
import tn.esprit.foyer.models.Foyer;
import tn.esprit.foyer.repositories.FoyerRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class FoyerServiceImpl implements FoyerService {
    @Autowired
    private final FoyerRepository foyerRepository;

    @Override
    public List<Foyer> getFoyersByNumerosChambres(List<Long> numerosChambres) {
        return foyerRepository.findFoyersByNumerosChambres(numerosChambres);
    }
    @Override
    public Foyer ajouterFoyerAvecBlocsAssociés(Foyer foyer) {
        if (foyer.getBlocs() != null) {
            for (Bloc bloc : foyer.getBlocs()) {
                bloc.setFoyer(foyer);
            }
        }
        return foyerRepository.save(foyer);
    }
}
