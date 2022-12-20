package jpabook.jpashop.repository.simplequery;

import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                        "select new jpabook.jpashop.repository.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) "
                                + "from Order o "
                                + "join o.member m "
                                + "join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
