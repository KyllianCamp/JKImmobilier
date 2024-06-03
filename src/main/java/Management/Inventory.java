package Management;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory", schema = "public")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInventory", nullable = false)
    private Integer idInventory;

    @ManyToOne
    @JoinColumn(name = "idLease")
    private Lease lease;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<StateElement> stateElement;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<StateRoom> stateRoom;

    public Inventory() {
    }
}
