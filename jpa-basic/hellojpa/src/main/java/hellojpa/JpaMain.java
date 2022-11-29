package hellojpa;

import hellojpa.jpql.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        try {
            Member memberA = new Member();
            memberA.setUsername("member1");
            memberA.setAge(10);
            em.persist(memberA);

            Member result = em.createQuery("select m from Member m where m.username = :username",
                            Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println(result);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
