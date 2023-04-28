package idusw.springboot.controller;

import idusw.springboot.domain.Memo;
import idusw.springboot.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemoControllerTests {
    @Autowired
    MemoService memoService;
    @Test
    public void readMemo() {
        Memo m = new Memo();
        m.setMno(1L);
        Memo result;
        if((result = memoService.read(m)) != null)
            System.out.println(result.getMemoText());
    }
}
