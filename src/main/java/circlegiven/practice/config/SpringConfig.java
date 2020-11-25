package circlegiven.practice.config;

import circlegiven.practice.repository.MemberRepository;
import circlegiven.practice.repository.MemoryMemberRepository;
import circlegiven.practice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
