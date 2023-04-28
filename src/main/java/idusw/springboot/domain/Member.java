package idusw.springboot.domain;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString
@EqualsAndHashCode

public class Member {
    private Long seq;
    private String email;
    private String name;
    private String pw;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
