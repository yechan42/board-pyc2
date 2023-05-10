package idusw.springboot.repository;


import idusw.springboot.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // JPQL
    @Query("select m from MemberEntity m where m.email = :email and m.pw = :pw")
    MemberEntity getByEmailPw(@Param("email") String email, @Param("pw") String pw);
}
//sql문으로 만들기
//select * from member_b학번 where email=? and pw=?
//
//? (placeholder)
//java.sql.*
//javax.sql.*
//질의문을 실행하는 객체, statement object
//(Statement, PreparedStatement, CallableStatement)
//
//pstmt.setString(1, "ychan@induk.ac.kr");
//pstmt.setString(2, "cometrue");
