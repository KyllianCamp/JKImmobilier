package Financial;

import jakarta.persistence.*;

@Entity
@Table(name = "rent", schema = "public")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRent", nullable = false)
    private Integer idRent;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "burdenIncluded")
    private Boolean burdenIncluded;

    @Column(name = "date")
    private String date;

    @Column(name = "dateFor")
    private String dateFor;

    @ManyToOne
    @JoinColumn(name = "idProperty")
    private Properties.Properties property;

    @OneToMany
    @JoinColumn(name = "idBurden")
    private Burden burden;

    @OneToMany
    @JoinColumn(name = "idPayment")
    private Payment payment;
}
