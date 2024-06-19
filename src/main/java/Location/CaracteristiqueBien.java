package Location;

import Persist.Persist;
import jakarta.persistence.*;

@Entity
@Table(name = "caracteristiqueBien")
public class CaracteristiqueBien extends Persist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idCaracteristique", referencedColumnName = "id")
    private Caracteristique caracteristique;

    @ManyToOne
    @JoinColumn(name = "idBien", referencedColumnName = "id")
    private Bien bien;

    public CaracteristiqueBien() {
    }

    @Override
    public String toString() {
        return "CaracteristiqueBien [id=" + id + ", caracteristique=" + caracteristique + "]";
    }

    public CaracteristiqueBien(Caracteristique caracteristique, Bien bien) {
        this.caracteristique = caracteristique;
        this.bien = bien;

        create(this);
    }

    public int getId() {
        return id;
    }

    public Caracteristique getCaracteristique() {
        return caracteristique;
    }

    public void delete() {
        delete(this);
    }

    
}
