package tn.esprit.foyer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;
    @Getter
    @Setter
    private String nomEt;
    private String prenomEt;
    private Long cin;
    private String ecole ;
    private Date dateNaissance;
    @ManyToMany(mappedBy = "etudiants")
    @JsonIgnore
    private List<Reservation> reservations;

}
