package bitcamp.java106.pms.controller.board;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;

@Component("board/add")
public class BoardAddController implements Controller {
    Scanner keyScan;
    BoardDao boardDao;
    
    public BoardAddController(Scanner scanner, BoardDao boardDao) {
        this.keyScan = scanner;
        this.boardDao = boardDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[게시물 입력]");
        Board board = new Board();

        System.out.print("제목? ");
        board.setTitle(this.keyScan.nextLine());

        System.out.print("내용? ");
        board.setContent(this.keyScan.nextLine());

        System.out.print("등록일? ");
        board.setCreatedDate(Date.valueOf(this.keyScan.nextLine()));

        boardDao.insert(board);
    }

}
