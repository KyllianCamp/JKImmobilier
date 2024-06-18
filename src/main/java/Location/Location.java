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

    @Column(name = "dateFin", nullable = true)
    private String dateFin;

    @Column(name = "commentaire")
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "idLocataire", referencedColumnName = "id", nullable = true)
    private Utilisateur locataire;

    @ManyToOne
    @JoinColumn(name = "idBien", referencedColumnName = "id")
    private Bien bien;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Paiement> paiements;

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Preavis preavis;

    public Location() {
    }

    public Location(String dateDebut, String dateFin, String commentaire, Bien bien) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.commentaire = commentaire;
        this.locataire = null;
        this.bien = bien;

        create(this);
    }

    public Location getLocationById(int id) {
        return (Location) read(this, id);
    }

    public int getId() {
        return id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Utilisateur getLocataire() {
        return locataire;
    }

    public Bien getBien() {
        return bien;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public Preavis getPreavis() {
        return preavis;
    }

    public void updateAll (int id, String dateDebut, String dateFin, String commentaire, Utilisateur locataire, Bien bien) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.commentaire = commentaire;
        this.locataire = locataire;
        this.bien = bien;

        update(this);
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", commentaire="
                + commentaire + ", locataire=" + locataire + ", bien=" + bien + ", paiements=" + paiements
                + ", preavis=" + preavis + "]";
    }
    
    
}
