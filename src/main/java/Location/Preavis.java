package Location;

import Persist.Persist;
import jakarta.persistence.*;

@Entity
@Table(name = "preavis")
public class Preavis extends Persist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "datePreavis")
    private String datePreavis;

    @Column(name = "dateDepart")
    private String dateDepart;

    @Column(name = "motif")
    private String motif;

    @OneToOne
    @JoinColumn(name = "idLocation", referencedColumnName = "id")
    private Location location;

    public Preavis() {
    }

    public Preavis(String datePreavis, String dateDepart, String motif, Location location) {
        this.datePreavis = datePreavis;
        this.dateDepart = dateDepart;
        this.motif = motif;
        this.location = location;

        create(this);
    }

    public String getDatePreavis() {
        return datePreavis;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public String getMotif() {
        return motif;
    }
}
