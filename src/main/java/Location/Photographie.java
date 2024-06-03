package Location;

import jakarta.persistence.*;

@Entity
@Table(name = "photographie")
public class Photographie {
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
}
