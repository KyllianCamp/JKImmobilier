package Properties;


import Management.StateRoom;
import Properties.Elements.Elements;
import Properties.Elements.Furnitures;
import jakarta.persistence.*;

@Entity
@Table(name = "rooms", schema = "public")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoom", nullable = false)
    private Integer idRoom;

    @Column(name = "description")
    private String description;

    @Column(name = "surface")
    private Double surface;

    @Column(name = "nbWall")
    private Integer nbWall;

    @Column(name = "nbWindows")
    private Integer nbWindows;

    @Column(name = "nbDoors")
    private Integer nbDoors;

    // @ManyToOne
    // @JoinColumn(name = "idProperty")
    // private Properties property;

    // @OneToMany
    // @JoinColumn(name = "idElement")
    // private Elements element;

    // @OneToMany
    // @JoinColumn(name = "idFurniture")
    // private Furnitures furniture;

    // @OneToMany
    // @JoinColumn(name = "idStateRoom")
    // private StateRoom stateRoom;

    public Rooms() {
    }
}
