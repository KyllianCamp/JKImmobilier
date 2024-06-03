package Persons;

import jakarta.persistence.*;

@Entity
@Table(name = "ownerproperty", schema = "public")
public class OwnerProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLink", nullable = false)
    private Integer idOwnerProperty;

    @ManyToOne
    @JoinColumn(name = "idOwner")
    private Tiers owner;

    @ManyToOne
    @JoinColumn(name = "idProperty")
    private Properties.Properties property;

    @Column(name = "current")
    private Boolean current;

    public OwnerProperty() {
    }
}
