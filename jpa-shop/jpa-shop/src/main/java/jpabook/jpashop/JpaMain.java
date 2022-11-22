package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Period;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shop");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            int a = 10;
            int b = 10;

            System.out.println("a == b : " + (a == b));

            Address address1 = new Address("city", "street", "10000");
            Address address2 = new Address("city", "street", "10000");

            System.out.println("address1 == address2 : " + (address1 == address2));
            System.out.println("address1 == address2 : " + (address1.equals(address2)));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
