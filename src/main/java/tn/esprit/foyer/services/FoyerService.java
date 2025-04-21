package tn.esprit.foyer.services;

import tn.esprit.foyer.models.Foyer;

import java.util.List;

public interface FoyerService {
    List<Foyer> getFoyersByNumerosChambres(List<Long> numerosChambres);
    public Foyer ajouterFoyerAvecBlocsAssociés (Foyer foyer);
}
