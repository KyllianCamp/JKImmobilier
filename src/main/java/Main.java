import Financial.Rent;
import Persons.Tiers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        System.out.println("Hello World");
        try {
            transaction.begin();
            Rent rent = entityManager.find(Rent.class, 1);
            System.out.println("test: " + rent.toString());
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            entityManager.getTransaction().rollback();
        }

        finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
