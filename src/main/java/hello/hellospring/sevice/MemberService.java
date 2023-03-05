package hello.hellospring.sevice;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
// CTL + SHIFT + T 하면 테스트 폴더를 자동으로 만들수 있다
    private final MemberRepository memberRepository= new MemoryMemberRepository();

    /*회원가입*/
    public Long join(Member member){
//        같은 이름이 있는 중복 회원은 안된다
        validateDuplicateMember(member); /*중복회원검증*/
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            /*reuslt 가 null 아닌 값일 때 동작한다. 이유는 optional이라서 null*/
            throw new IllegalStateException("이미 존재 합니다.");
        });
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
