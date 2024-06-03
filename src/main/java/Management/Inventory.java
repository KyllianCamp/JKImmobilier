package Management;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory", schema = "public")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInventory", nullable = false)
    private Integer idInventory;

    // @ManyToOne
    // @JoinColumn(name = "idLease")
    // private Lease lease;

    // @OneToMany
    // @JoinColumn(name = "idInventory")
    // private StateElement stateElement;

    // @OneToMany
    // @JoinColumn(name = "idInventory")
    // private StateRoom stateRoom;

    public Inventory() {
    }
}
