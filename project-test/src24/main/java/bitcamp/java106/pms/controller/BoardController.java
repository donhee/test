package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.Console;

@Component("board")
public class BoardController implements Controller {
    Scanner keyScan;
    BoardDao boardDao;
    
    public BoardController(Scanner scanner, BoardDao boardDao) {
        this.keyScan = scanner;
        this.boardDao = boardDao;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("board/add")) {
            this.onBoardAdd();
        } else if (menu.equals("board/list")) {
            this.onBoardList();
        } else if (menu.equals("board/view")) {
            this.onBoardView(option);
        } else if (menu.equals("board/update")) {
            this.onBoardUpdate(option);
        } else if (menu.equals("board/delete")) {
            this.onBoardDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    void onBoardAdd() {
        System.out.println("[게시물 입력]");
        
        Board board = new Board();

        System.out.print("제목? ");
        board.setTitle(keyScan.nextLine());
        
        System.out.print("내용? ");
        board.setContent(keyScan.nextLine());
        
        System.out.print("등록일? ");
        board.setCreatedDate(Date.valueOf(keyScan.nextLine()));
        
        boardDao.insert(board);
    }
    
    void onBoardList() {
        System.out.println("[게시물 목록]");
        Iterator<Board> iterator = boardDao.list();
        while (iterator.hasNext()) {
            Board board = iterator.next();
            System.out.printf("%d, %s, %s\n",
                board.getNo(), board.getTitle(), board.getCreatedDate());
        }
    }
    
    void onBoardView(String option) {
        System.out.println("[게시물 조회]");
        
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        Board board = boardDao.get(Integer.parseInt(option));
        
        if (board == null) {
            System.out.println("해당 게시물 번호가 없습니다.");
        } else {
            System.out.printf("제목: %s\n", board.getTitle());
            System.out.printf("내용: %s\n", board.getContent());
            System.out.printf("시작일: %s\n", board.getCreatedDate());
        }
    }
    
    void onBoardUpdate(String option) {
        System.out.println("[게시물 변경]");
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        Board board = boardDao.get(Integer.parseInt(option));
        if (board == null) {
            System.out.println("해당 게시물 번호가 없습니다.");
        } else {
            Board updateBoard = new Board();
            System.out.printf("제목(%s)? ", board.getTitle());
            updateBoard.setTitle(keyScan.nextLine());
            System.out.printf("내용(%s)? ", board.getContent());
            updateBoard.setContent(keyScan.nextLine());
            System.out.printf("시작일(%s)? ", board.getCreatedDate());
            updateBoard.setCreatedDate(Date.valueOf(keyScan.nextLine()));

            int index = boardDao.indexOf(board.getNo());
            boardDao.update(index, updateBoard);
            System.out.println("변경하였습니다.");
        }
    }

    void onBoardDelete(String option) {
        System.out.println("[게시물 삭제]");
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        int i = Integer.parseInt(option);
        Board board = boardDao.get(i);
        
        if (board == null) {
            System.out.println("해당 게시물이 없습니다.");
        } else {
            if (Console.confirm("정말 삭제하시겠습니까?")) {
                boardDao.delete(i);
                System.out.println("삭제하였습니다.");
            }
        }
    }
    
}
