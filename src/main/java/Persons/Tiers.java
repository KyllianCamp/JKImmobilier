package Persons;

import jakarta.persistence.*;

@Entity
@Table(name = "tiers", schema = "public")
public class Tiers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTiers", nullable = false)
    private Integer idTiers;

    @Column(name = "lastName")
    private String lastname;

    @Column(name = "firstName")
    private String firstname;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "mail")
    private String mail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "rib")
    private String rib;

    @Column(name = "iban")
    private String iban;

    @Column(name = "bic")
    private String bic;

    @OneToMany
    @JoinColumn(name = "idPayment")
    private Financial.Payment payment;

    @OneToMany
    @JoinColumn(name = "idOwner")
    private OwnerProperty ownerProperty;

    public Tiers(String lastname, String firstname, String dateOfBirth, String mail, String phone, String rib, String iban, String bic) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.dateOfBirth = dateOfBirth;
        this.mail = mail;
        this.phone = phone;
        this.rib = rib;
        this.iban = iban;
        this.bic = bic;
    }

    public Tiers() {

    }
}
