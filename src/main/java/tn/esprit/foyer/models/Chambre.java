package tn.esprit.foyer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;
    private Long numeroChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre typeC ;
    @ManyToOne()
    @JsonIgnore
    private Bloc bloc;
    @OneToMany
    @JsonIgnore
    private List<Reservation> reservations;
// rrelation unidirectionnel kn mn chirat el pere el fils ma chof chy
    public Chambre() {

    }

    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    public Chambre(Long idChambre, Long numeroChambre, TypeChambre typeC, Bloc bloc, List<Reservation> reservations) {
        this.idChambre = idChambre;
        this.numeroChambre = numeroChambre;
        this.typeC = typeC;
        this.bloc = bloc;
        this.reservations = reservations;
    }

    public Long getIdChambre() {
        return idChambre;
    }

    public Long getNumeroChambre() {
        return numeroChambre;
    }

    public TypeChambre getTypeC() {
        return typeC;
    }

    public Bloc getBloc() {
        return bloc;
    }

    public void setNumeroChambre(Long numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public void setBloc(Bloc bloc) {
        this.bloc = bloc;
    }

    public void setTypeC(TypeChambre typeC) {
        this.typeC = typeC;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
