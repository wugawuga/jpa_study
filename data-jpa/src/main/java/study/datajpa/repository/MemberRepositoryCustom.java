package study.datajpa.repository;

import java.util.List;
import study.datajpa.entity.Member;

public interface MemberRepositoryCustom {

    List<Member> findMemberCustom();
}
