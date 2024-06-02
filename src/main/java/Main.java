import Persons.Tiers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Tiers tiers = new Tiers(
                    "Michka",
                    "Michka",
                    "1999-01-01",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test"
            );
            entityManager.persist(tiers);
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
