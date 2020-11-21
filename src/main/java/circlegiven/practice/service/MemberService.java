package circlegiven.practice.service;

import circlegiven.practice.domain.Member;
import circlegiven.practice.repository.MemberRepository;
import circlegiven.practice.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {
        // 중복이름 체크
        validateDuplicateMember(member);
        repository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findMember(Long memberId) {
        return repository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 이름");
            });
    }
}
