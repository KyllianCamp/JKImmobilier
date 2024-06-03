package Persons;

import java.util.List;

import Financial.Payment;
import Management.Lease;
import Persist.Persist;
import jakarta.persistence.*;

@Entity
@Table(name = "tiers", schema = "public")
public class Tiers extends Persist{

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

    @OneToMany(mappedBy = "tiers", cascade = CascadeType.ALL)
    private List<Payment> payment;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<OwnerProperty> ownerProperty;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<Lease> lease;

    public Tiers() {}

    public Tiers(String lastname, String firstname, String dateOfBirth, String mail, String phone, String rib, String iban, String bic) {
        if (lastname == null || firstname == null || dateOfBirth == null || mail == null || phone == null || rib == null || iban == null || bic == null) {
            throw new IllegalArgumentException("All fields must be filled");
        }
        this.lastname = lastname;
        this.firstname = firstname;
        this.dateOfBirth = dateOfBirth;
        this.mail = mail;
        this.phone = phone;
        this.rib = rib;
        this.iban = iban;
        this.bic = bic;

        create(this);
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getRib() {
        return rib;
    }

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        update(this);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        update(this);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        update(this);
    }

    public void setMail(String mail) {
        this.mail = mail;
        update(this);
    }

    public void setPhone(String phone) {
        this.phone = phone;
        update(this);
    }

    public void setRib(String rib) {
        this.rib = rib;
        update(this);
    }

    public void setIban(String iban) {
        this.iban = iban;
        update(this);
    }

    public void setBic(String bic) {
        this.bic = bic;
        update(this);
    }

    public void delete() {
        delete(this);
    }

    public Tiers read() {
        return (Tiers) read(this, this.idTiers);
    }

    @Override
    public String toString() {
        return "Tiers [idTiers=" + idTiers + ", lastname=" + lastname + ", firstname=" + firstname + ", dateOfBirth="
                + dateOfBirth + ", mail=" + mail + ", phone=" + phone + ", rib=" + rib + ", iban=" + iban + ", bic="
                + bic + "]";
    }
}
