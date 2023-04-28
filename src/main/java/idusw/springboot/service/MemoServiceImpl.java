package idusw.springboot.service;

import idusw.springboot.domain.Memo;
import idusw.springboot.entity.MemoEntity;
import idusw.springboot.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service// @Service : stereo type, Spring Framework에게 컴포넌트임을 알려줌
public class MemoServiceImpl implements MemoService {
    MemoRepository memoRepository;
    public MemoServiceImpl(MemoRepository memoRepository) { // 생성자 주입
        // Spring Framework가 MemoRepository 인터페이스를 구현한 인스턴스를 배정함
        this.memoRepository = memoRepository;
    }

    @Override
    public int create(Memo m) {
        return 0;
    }

    @Override
    public Memo read(Memo m) {
        MemoEntity e = memoRepository.getById(m.getMno());
        Memo memo = new Memo(); // DTO (Data Transfer Object) : Controller - Service or Controller - View
        memo.setMno(e.getMno());
        memo.setMemoText(e.getMemoText());
        return memo;
    }

    @Override
    public List<Memo> readList() {
        // 테이블로 부터 모두 읽어와 객체의 리스트로 반환
        // entity : Serivce <-> Repository
        List<Memo> result = new ArrayList<>(); // Memo 객체를 원소로 갖는 리스트형 객체를 생성, 배정
        List<MemoEntity> entities = memoRepository.findAll(); // select * from a_memo;
        for(MemoEntity e : entities) {
            Memo m = new Memo(); // DTO (Data Transfer Object) : Controller - Service or Controller - View
            m.setMno(e.getMno());
            m.setMemoText(e.getMemoText());
            result.add(m);
        }
        return result;
    }

    @Override
    public int update(Memo m) {
        return 0;
    }

    @Override
    public int delete(Memo m) {
        return 0;
    }

    @Override
    public List<Memo> initialize() {
        // 테이블 초기화 코드
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemoEntity memo = MemoEntity.builder().memoText("egyou : " + i).build();
            memoRepository.save(memo);
        });
        return readList();
    }
}
