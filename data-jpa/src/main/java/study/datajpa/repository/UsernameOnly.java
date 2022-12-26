package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

    //오픈프로젝션 엔티티 정보 다 긁어옴
    @Value("#{target.username + ' ' + target.age}")
    String getUsername();
}
