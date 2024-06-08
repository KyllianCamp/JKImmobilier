package Location;

import java.util.List;
import Persist.*;

import jakarta.persistence.*;

@Entity
@Table(name = "bien")
public class Bien extends Persist{
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
    private String description;

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

    public Bien() {
    }

    public Bien(String nom, String adresse, String codePostal, int nbPieces, int surface, String description, int loyer, String type, Utilisateur proprietaire) {
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.nbPieces = nbPieces;
        this.surface = surface;
        this.description = description;
        this.loyer = loyer;
        this.type = type;
        this.proprietaire = proprietaire;

        create(this);
    }

    public Bien(int id, String nom, String adresse, String codePostal, int nbPieces, int surface, String description, int loyer, String type) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.nbPieces = nbPieces;
        this.surface = surface;
        this.description = description;
        this.loyer = loyer;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        update(this);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
        update(this);
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
        update(this);
    }

    public int getNbPieces() {
        return nbPieces;
    }

    public void setNbPieces(int nbPieces) {
        this.nbPieces = nbPieces;
        update(this);
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
        update(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        update(this);
    }

    public int getLoyer() {
        return loyer;
    }

    public void setLoyer(int loyer) {
        this.loyer = loyer;
        update(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        update(this);
    }

    public void delete() {
        delete(this);
    }
    
}
