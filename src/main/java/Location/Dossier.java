package Location;

import jakarta.persistence.*;

@Entity
@Table(name = "dossier")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dateSoumission")
    private String dateSoumission;

    @Column(name = "statut")
    private String statut;

    @ManyToOne
    @JoinColumn(name = "idCandidat", referencedColumnName = "id")
    private Utilisateur candidat;

    @ManyToOne
    @JoinColumn(name = "idBien", referencedColumnName = "id")
    private Bien bien;

}
