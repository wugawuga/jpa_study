package hellojpa;

import static hellojpa.jpql.MemberType.ADMIN;

import hellojpa.jpql.Member;
import hellojpa.jpql.MemberDto;
import hellojpa.jpql.MemberType;
import hellojpa.jpql.Team;
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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member memberA = new Member();
            memberA.setUsername("member1");
            memberA.setAge(10);
            memberA.setTeam(team);
            memberA.setType(ADMIN);

            em.persist(memberA);

            em.flush();
            em.clear();

            String query = "select m.username, 'hello', TRUE from Member m "
                    + "where m.type = :usertype";
            List<Object[]> result = em.createQuery(query)
                    .setParameter("userType", ADMIN)
                    .getResultList();

            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
