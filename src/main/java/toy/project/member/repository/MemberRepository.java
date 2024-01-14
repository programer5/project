package toy.project.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    Optional<Member> existsByUsername(String username);
}
