package com.example.springboot_oracle_jpa.service;


import com.example.springboot_oracle_jpa.domain.Member;
import com.example.springboot_oracle_jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)// 읽기 전용이 많기 때문에 readOnly true로 설정
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입

    @Transactional // 여기만 수정 가능하게 따로 기입해줌.(기본값 READONLY FALSE)
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("already used.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){// 한개 조회
        return memberRepository.findOne(memberId);
    }
}
