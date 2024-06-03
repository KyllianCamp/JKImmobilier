package Properties;

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

    @OneToMany
    @JoinColumn(name = "idRoom")
    private Rooms room;

    @OneToMany
    @JoinColumn(name = "idRent")
    private Financial.Rent rent;

    @OneToMany
    @JoinColumn(name = "idOwner")
    private Persons.OwnerProperty ownerProperty;

    public Properties(String creationDate, Double surface, Integer nbRooms) {
        this.creationDate = creationDate;
        this.surface = surface;
        this.nbRooms = nbRooms;
    }

    public Properties() {
    }
}
