package Management;

import Properties.Rooms;
import jakarta.persistence.*;

@Entity
@Table(name = "stateroom", schema = "public")
public class StateRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStateRoom", nullable = false)
    private Integer idStateRoom;

    @Column(name = "date")
    private String date;

    @Column(name = "hour")
    private String hour;

    @Column(name = "description")
    private String description;

    // @ManyToOne
    // @JoinColumn(name = "idInventory")
    // private Inventory inventory;

    // @ManyToOne
    // @JoinColumn(name = "idRoom")
    // private Rooms room;

    public StateRoom() {
    }
}
