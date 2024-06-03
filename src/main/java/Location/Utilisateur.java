package Location;

import java.util.List;
import Persist.*;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur extends Persist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "locataire", cascade = CascadeType.ALL)
    private List<Location> locations;

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL)
    private List<Bien> biens;

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
    private List<Dossier> dossiers;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String mail, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;

        create(this);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        update(this);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
        update(this);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
        update(this);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
        update(this);
    }

    public void delete() {
        delete(this);
    }


}
