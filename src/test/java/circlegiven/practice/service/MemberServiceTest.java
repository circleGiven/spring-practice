package circlegiven.practice.service;

import circlegiven.practice.domain.Member;
import circlegiven.practice.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("circlegiven");

        // when
        Long savedId = memberService.join(member );

        // then
        Member result = memberService.findMember(savedId).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    void 중복회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("circlegiven");

        Member member2 = new Member();
        member2.setName("circlegiven");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//        try {
//            memberService.join(member2);
//            fail("예외 발생");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름");
//        }

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름");
    }
}