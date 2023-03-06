package hello.hellospring.sevice;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

        MemberService memberService ;

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();


        @BeforeEach
       public void beforeEach(){
            memberRepository = new MemoryMemberRepository();
            memberService = new MemberService(memberRepository);
            /*불러와서 생성한다 DI*/
        }
        @AfterEach
        public void afterEach(){
            memberRepository.clearStore();
        }
    @Test
    void join() {
//        given
        Member member = new Member();
        member.setName("spring");
//        when
        Long saveId = memberService.join(member);

//        then
        Member findMember =  memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("test");

        Member member2 = new Member();
        member2.setName("test");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows( IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재 합니다.");
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}