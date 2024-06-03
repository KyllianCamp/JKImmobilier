package Management;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "idAgent", referencedColumnName = "idTiers")
    private Persons.Tiers agent;

    @OneToMany(mappedBy = "lease", cascade = CascadeType.ALL)
    private List<Inventory> inventory;

    public Lease() {
    }
}
