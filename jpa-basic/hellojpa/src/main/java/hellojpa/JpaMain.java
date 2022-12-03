package hellojpa;

import hellojpa.jpql.Member;
import hellojpa.jpql.MemberDto;
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

            em.persist(memberA);

            em.flush();
            em.clear();

            String query = "select (select avg(m1.age) from Member m1) from Member m join Team t on m.username = t.name";
            em.createQuery(query, Member.class);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
