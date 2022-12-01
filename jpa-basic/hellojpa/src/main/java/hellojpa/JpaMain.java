package hellojpa;

import hellojpa.jpql.Member;
import hellojpa.jpql.MemberDto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            em.flush();
            em.clear();

            Member singleResult = em.createQuery(
                    "select m from Member m join m.team t", Member.class).getSingleResult();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
