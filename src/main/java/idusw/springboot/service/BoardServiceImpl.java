package idusw.springboot.service;

import idusw.springboot.domain.Board;
import idusw.springboot.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class BoardServiceImpl implements BoardService {
 private BoardRepository boardRepository;
    public BoardServiceImpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    @Override
    public int registerBoard(Board board) {

        return 0;
    }

    @Override
    public Board findBoardById(Board board) {

        return null;
    }

    @Override
    public List<Board> findBoardAll() {

        return null;
    }

    @Override
    public int updateBoard(Board board) {

        return 0;
    }

    @Override
    public int deleteBoard(Board board) {

        return 0;
    }
}
