package Location;

import Persist.Persist;
import jakarta.persistence.*;

@Entity
@Table(name = "photographie")
public class Photographie extends Persist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lien")
    private String lien;

    @ManyToOne
    @JoinColumn(name = "idBien", referencedColumnName = "id")
    private Bien bien;

    public Photographie() {
    }

    public Photographie(String lien, Bien bien) {
        this.lien = lien;
        this.bien = bien;

        create(this);
    }

    public String getLien() {
        return lien;
    }
}
