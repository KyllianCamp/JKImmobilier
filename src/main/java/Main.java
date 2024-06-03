import Financial.Rent;
import Persons.Tiers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);

        Tiers test = new Tiers("Doe", "John", "2000-01-01", "test@gmail.com", "0600000000", "123456789", "FR123456789", "FR123456789");
        System.out.println("test: " + test.toString());
        test.setLastname("update");
        System.out.println(test.read().toString());
        test.delete();
    }
}
