package Management;

import Properties.Elements.Elements;
import jakarta.persistence.*;   

@Entity
@Table(name = "stateelement", schema = "public")
public class StateElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStateElement", nullable = false)
    private Integer idStateElement;

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
    // @JoinColumn(name = "idElement")
    // private Elements element;

    public StateElement() {
    }
}
