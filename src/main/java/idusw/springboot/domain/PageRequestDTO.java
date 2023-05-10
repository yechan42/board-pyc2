package idusw.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Builder
@AllArgsConstructor
@Data

public class PageRequestDTO {
    private int page; // 요청하는 페이지
    private int size; // 페이지당 게시물 수

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;

    }
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size,sort); //of 메소드 사용 (-1을 사용하는 이유 *컴퓨터 배열은 0부터 시작하기 때문에)


    }
}
