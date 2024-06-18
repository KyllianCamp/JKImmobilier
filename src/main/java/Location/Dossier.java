package Location;

import Persist.Persist;
import jakarta.persistence.*;

@Entity
@Table(name = "dossier")
public class Dossier extends Persist {
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

    public Dossier() {
    }

    public Dossier(String dateSoumission, String statut, Utilisateur candidat, Bien bien) {
        this.dateSoumission = dateSoumission;
        this.statut = statut;
        this.candidat = candidat;
        this.bien = bien;

        create(this);
    }

    public Dossier getDossierById(int id) {
        return (Dossier) read(Dossier.class, id);
    }

    public int getId() {
        return id;
    }

    public String getDateSoumission() {
        return dateSoumission;
    }

    public String getStatut() {
        return statut;
    }

    public Utilisateur getCandidat() {
        return candidat;
    }

    public Bien getBien() {
        return bien;
    }

    public void setStatut(String statut) {
        this.statut = statut;
        update(this);
    }

    

}
