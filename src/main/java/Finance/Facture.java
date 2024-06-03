package Finance;

import jakarta.persistence.*;

@Entity
@Table(name = "facture")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numeroFacture")
    private int numeroFacture;

    @Column(name = "prixHT")
    private Double prixHT;

    @ManyToOne
    @JoinColumn(name = "idPaiement", referencedColumnName = "id")
    private Paiement paiement;

}
