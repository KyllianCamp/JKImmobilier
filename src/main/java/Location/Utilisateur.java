package Location;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
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
}
