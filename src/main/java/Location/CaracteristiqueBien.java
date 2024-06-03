package Location;

import jakarta.persistence.*;

@Entity
@Table(name = "caracteristiqueBien")
public class CaracteristiqueBien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idCaracteristique", referencedColumnName = "id")
    private Caracteristique caracteristique;

    @ManyToOne
    @JoinColumn(name = "idBien", referencedColumnName = "id")
    private Bien bien;
}
