package idusw.springboot.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import idusw.springboot.domain.Member;
import idusw.springboot.domain.PageRequestDTO;
import idusw.springboot.domain.PageResultDTO;
import idusw.springboot.entity.MemberEntity;
import idusw.springboot.entity.QMemberEntity;
import idusw.springboot.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    @Override
    public PageResultDTO<Member, MemberEntity> getList(PageRequestDTO requestDTO) {
        Sort sort = Sort.by("seq").descending();
        /*
        if(requestDTO.getSort() == null)
            sort = Sort.by("seq").descending();
        else
            sort = Sort.by("seq").ascending();

         */
        Pageable pageable = requestDTO.getPageable(sort);
        //Page<MemberEntity> result = memberRepository.findAll(pageable);

        BooleanBuilder booleanBuilder =  findByCondition(requestDTO);
        Page<MemberEntity> result = memberRepository.findAll(booleanBuilder, pageable);

        Function<MemberEntity, Member> fn = (entity -> entityToDto(entity));

        PageResultDTO pageResultDTO = new PageResultDTO<>(result, fn, requestDTO.getPerPagination());

        return pageResultDTO;
    }
    private BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) { //Condition (검색 조건)을 QueryDSL을 활용하여 객체로 생성
            String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;
        BooleanExpression expression = qMemberEntity.seq.gt(0L); // where seq > 0 and title == "title"
        booleanBuilder.and(expression);
        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }
        String keyword = pageRequestDTO.getKeyword();

        System.out.println("findByCondition " + type + " : " + keyword);

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("e")) { // email로 검색
            conditionBuilder.or(qMemberEntity.email.contains(keyword));
        }
        if(type.contains("n")) { // email로 검색
            conditionBuilder.or(qMemberEntity.name.contains(keyword));
        }
        /*
        if(type.contains("p")) { // phone로 검색
            conditionBuilder.or(qMemberEntity.phone.contains(keyword));
        }
        if(type.contains("a")) { // address로 검색
            conditionBuilder.or(qMemberEntity.address.contains(keyword));
        } // 조건을 전부 줄 수도 있으니 if else문 아님
        if(type.contains("l")) {
            conditionBuilder.or(qMemberEntity.level.contains(keyword));
        }
         */
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

}
