package tn.esprit.foyer.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private String idReservation; // lorsque l id est de type string generatide value mfmch
    private Date anneeUniversitaire;
    private  Boolean estValide;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;
}
