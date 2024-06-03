package Location;

import java.util.List;
import Persist.*;

import Finance.Paiement;
import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location extends Persist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dateDebut")
    private String dateDebut;

    @Column(name = "dateFin")
    private String dateFin;

    @Column(name = "commentaire")
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "idLocataire", referencedColumnName = "id")
    private Utilisateur locataire;

    @ManyToOne
    @JoinColumn(name = "idBien", referencedColumnName = "id")
    private Bien bien;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Paiement> paiements;

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
    private Preavis preavis;

    public Location() {
    }

    public Location(String dateDebut, String dateFin, String commentaire, Utilisateur locataire, Bien bien) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.commentaire = commentaire;
        this.locataire = locataire;
        this.bien = bien;

        create(this);
    }
}
