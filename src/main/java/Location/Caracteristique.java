package Location;

import java.util.List;

import Persist.Persist;
import jakarta.persistence.*;

@Entity
@Table(name = "caracteristique")
public class Caracteristique extends Persist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "caracteristique", cascade = CascadeType.ALL)
    private List<CaracteristiqueBien> caracteristiqueBiens;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Caracteristique() {
    }

    public Caracteristique(String nom) {
        this.nom = nom;

        create(this);
    }

    public Caracteristique getCaracteristiqueById(int id) {
        return (Caracteristique) read(Caracteristique.class, id);
    }

    @Override
    public String toString() {
        return "Caracteristique [id=" + id + ", nom=" + nom + "]";
    }
}
