package Location;

import java.util.List;

import Finance.Paiement;
import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
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
}
