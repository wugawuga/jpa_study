package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;

@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
