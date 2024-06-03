package Finance;

import java.util.List;

import Location.Location;
import jakarta.persistence.*;

@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "etat")
    private String etat;

    @Column(name = "rib")
    private String rib;

    @Column(name = "date")
    private String date;
    
    @ManyToOne
    @JoinColumn(name = "idLocation", referencedColumnName = "id")
    private Location location;

    @OneToMany(mappedBy = "paiement", cascade = CascadeType.ALL)
    private List<Facture> facture;
}
