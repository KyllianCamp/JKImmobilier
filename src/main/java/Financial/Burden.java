package Financial;

import jakarta.persistence.*;

@Entity
@Table(name = "burden", schema = "public")
public class Burden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBurden", nullable = false)
    private Integer idBurden;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idRent")
    private Rent rent;

    public Burden() {
    }
}
