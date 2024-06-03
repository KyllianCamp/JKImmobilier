package Location;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "bien")
public class Bien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "codePostal")
    private String codePostal;
    
    @Column(name = "nbPieces")
    private int nbPieces;

    @Column(name = "surface")
    private int surface;

    @Column(name = "description")
    private int description;

    @Column(name = "loyer")
    private int loyer;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "idProprietaire", referencedColumnName = "id")
    private Utilisateur proprietaire;

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL)
    private List<Location> locations;

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL)
    private List<CaracteristiqueBien> caracteristiqueBiens;

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL)
    private List<Dossier> dossiers;

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL)
    private List<Photographie> photographies;
}
