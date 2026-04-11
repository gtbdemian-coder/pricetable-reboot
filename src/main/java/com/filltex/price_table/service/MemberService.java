package com.filltex.price_table.service;

import com.filltex.price_table.domain.Member;
import com.filltex.price_table.dto.MemberDto;
import com.filltex.price_table.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public Long join(MemberDto memberDto) {

        memberRepository.findByLoginId(memberDto.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });

        Member member = memberDto.toEntity();
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 로그인
     */
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
