package Properties.Elements;

import Management.StateElement;
import Properties.Rooms;
import jakarta.persistence.*;

@Entity
@Table(name = "elements", schema = "public")
public class Elements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idElement", nullable = false)
    private Integer idElement;

    @Column(name = "type")
    private String type;

    @Column(name = "direction")
    private String direction;

    @Column(name = "trapPresence")
    private Boolean trapPresence;

    @Column(name = "numWall")
    private Integer quantity;

    @Column(name = "nbWindows")
    private Integer nbWindows;

    @Column(name = "nbDoors")
    private Integer nbDoors;

    @Column(name = "nbHeaters")
    private Integer nbHeaters;

    @ManyToOne
    @JoinColumn(name = "idRoom")
    private Rooms room;

    @OneToMany
    @JoinColumn(name = "idWallElement")
    private WallElements wallElement;

    @OneToMany
    @JoinColumn(name = "idStateElement")
    private StateElement stateElement;

    public Elements() {
    }
}
