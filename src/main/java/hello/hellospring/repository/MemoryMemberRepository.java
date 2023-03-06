package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
//Optional.ofMullable를 사용하면 안의 있는 null이 여도 감 싸서 반환해준다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
//                루프로 돌린다
                .filter(member -> member.getName().equals(name))
                .findAny();
//        equals를 사용하여 비교를 한다
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        /*멤버 반환*/
    }

    public void clearStore(){
        store.clear();
//        스토어를 싹 비운다
    }
}
