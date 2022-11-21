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
            Address address = new Address("오산", "street", "1111");

            Member member = new Member();
            member.setName("wuga");
            member.setAddress(address);
            member.setPeriod(new Period());
            em.persist(member);

            Member member2 = new Member();
            member2.setName("wuga2");

            Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            member2.setAddress(address2);
            em.persist(member2);

            member2.getAddress().setCity("newCity");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
