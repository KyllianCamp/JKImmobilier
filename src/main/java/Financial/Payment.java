package Financial;

import Persons.Tiers;
import jakarta.persistence.*;

@Entity
@Table(name = "payment", schema = "public")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPayment", nullable = false)
    private Integer idPayment;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private String date;

    @Column(name = "dateFor")
    private String dateFor;

    @Column(name = "rib")
    private String rib;

    // @ManyToOne
    // @JoinColumn(name = "idRent")
    // private Rent rent;

    // @ManyToOne
    // @JoinColumn(name = "idTiers")
    // private Tiers tiers;
}
