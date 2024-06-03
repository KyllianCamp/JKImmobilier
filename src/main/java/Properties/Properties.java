package Properties;

import java.util.List;

import Financial.Payment;
import Financial.Rent;
import Persons.OwnerProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "properties", schema = "public")
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProperties", nullable = false)
    private Integer idProperties;

    @Column(name = "creationDate")
    private String creationDate;

    @Column(name = "surface")
    private Double surface;

    @Column(name = "nbRooms")
    private Integer nbRooms;

    @OneToOne
    @JoinColumn(name = "idAddress")
    private Adresses address;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Rooms> room;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Rent> rent;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<OwnerProperty> ownerProperty;

    public Properties(String creationDate, Double surface, Integer nbRooms) {
        this.creationDate = creationDate;
        this.surface = surface;
        this.nbRooms = nbRooms;
    }

    public Properties() {
    }
}
