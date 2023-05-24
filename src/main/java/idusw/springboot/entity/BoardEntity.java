package idusw.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity // 엔티티 클래스임으로 나타내는 애노테이션
@Table(name = "b_board201918061")

@ToString   // lombok 라이브러리 사용
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "b_board201918061_seq_gen")
    @SequenceGenerator(sequenceName = "b_board201918061_seq", name = "b_board201918061_seq_gen",
            initialValue = 1, allocationSize = 1)
    // Oracle : GenerationType.SEQUENCE, Mysql : GenerationType.IDENTITY
    private Long bno;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 10000, nullable = false)
    private String content;

    @ManyToOne
   // @JoinColumn(name = "seq")
    private MemberEntity writer; //BoardEntity : MemberEntity = N : 1 관계

}
