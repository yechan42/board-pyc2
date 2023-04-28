package idusw.springboot.service;

import idusw.springboot.domain.Member;
import idusw.springboot.entity.MemberEntity;
import idusw.springboot.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    // DI - IoC (Inversion of Control : 제어의 역전) 방법 중 하나, DI, DL ...
    //
    MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) { // Spring Framework이 주입(하도록 요청함)
        this.memberRepository = memberRepository;
    }

    @Override
    public int create(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .email(m.getEmail())
                .name(m.getName())
                .pw(m.getPw())
                .build();
        if(memberRepository.save(entity) != null) // 저장 성공
            return 1;
        else
            return 0;
    }

    @Override
    public Member read(Member m) {
        MemberEntity e = memberRepository.getById(m.getSeq()); // JpaRepository 구현체의 메소드
        Member result = new Member(); // DTO (Data Transfer Object) : Controller - Service or Controller - View
        System.out.println(e);
        result.setSeq(e.getSeq());
        result.setEmail(e.getEmail());
        result.setName(e.getName());
        return result;
    }

    @Override
    public List<Member> readList() {
        List<MemberEntity> entities = new ArrayList<>();
        List<Member> members = null;
        if((entities = memberRepository.findAll()) != null) {
            members = new ArrayList<>();
            for(MemberEntity e : entities) {
                Member m = Member.builder()
                        .seq(e.getSeq())
                        .email(e.getEmail())
                        .name(e.getName())
                        .pw(e.getPw())
                        .regDate(e.getRegDate())
                        .modDate(e.getModDate())
                        .build();
                members.add(m);
            }
        }
        return members;
    }

    @Override
    public int update(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .email(m.getEmail())
                .name(m.getName())
                .pw(m.getPw())
                .build();
        if(memberRepository.save(entity) != null) // 저장 성공
            return 1;
        else
            return 0;
    }

    @Override
    public int delete(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .build();
        memberRepository.deleteById(entity.getSeq());
        return 1;
    }

    @Override
    public Member login(Member m) {
        MemberEntity e = memberRepository.getByEmailPw(m.getEmail(), m.getPw()); // JpaRepository 구현체의 메소드
        System.out.println("login : " + e);
        Member result = null; // DTO (Data Transfer Object) : Controller - Service or Controller - View
        if(e != null) {
            result = new Member();
            result.setSeq(e.getSeq());
            result.setEmail(e.getEmail());
            result.setName(e.getName());
        }
        return result;
    }

}
