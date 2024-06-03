package Properties;


import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "idProperty")
    private Properties property;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Elements> element;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Furnitures> furniture;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<StateRoom> stateRoom;

    public Rooms() {
    }
}
