package hellojpa;

import static hellojpa.jpql.MemberType.ADMIN;

import hellojpa.jpql.Member;
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

            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "member1")
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member.getUsername() = " + member.getUsername());
                System.out.println("member.getAge() = " + member.getAge());
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
