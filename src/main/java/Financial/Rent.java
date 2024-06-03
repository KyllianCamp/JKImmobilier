package Financial;

import java.util.List;

import Properties.Properties;
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
    private Properties property;

    @OneToMany(mappedBy = "rent", cascade = CascadeType.ALL)
    private List<Burden> burden;

    @OneToMany(mappedBy = "rent", cascade = CascadeType.ALL)
    private List<Payment> payment;

    @Override
    public String toString() {
        return "Rent [idRent=" + idRent + ", amount=" + amount + ", burdenIncluded=" + burdenIncluded + ", date=" + date
                + ", dateFor=" + dateFor + ", burden=" + burden + "]";
    }

}
