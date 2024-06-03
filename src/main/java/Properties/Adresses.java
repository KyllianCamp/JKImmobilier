package Properties;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses", schema = "public")
public class Adresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAddress", nullable = false)
    private Integer idAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "streetName")
    private String streetName;

    @Column(name = "numStreet")
    private Integer numStreet;

    @Column(name = "moreInfo") 
    private String moreInfo;

    @OneToOne()
    @JoinColumn(name = "idProperty")
    private Properties property;

    public Adresses() {
    }
}
