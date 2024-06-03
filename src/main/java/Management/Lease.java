package Management;

import jakarta.persistence.*;

@Entity
@Table(name = "lease", schema = "public")
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLease", nullable = false)
    private Integer idLease;

    @Column(name = "date")
    private String date;

    @Column(name = "numLease")
    private Integer numLease;

    // @ManyToOne
    // @JoinColumn(name = "idAgent")
    // private Persons.Tiers agent;

    // @OneToMany
    // @JoinColumn(name = "idLease")
    // private Inventory inventory;

    public Lease() {
    }
}
