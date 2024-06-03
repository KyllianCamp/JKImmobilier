package Location;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "caracteristique")
public class Caracteristique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "caracteristique", cascade = CascadeType.ALL)
    private List<CaracteristiqueBien> caracteristiqueBiens;
}
