package Location;

import jakarta.persistence.*;

@Entity
@Table(name = "preavis")
public class Preavis {
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
}
