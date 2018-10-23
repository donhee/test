package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.Dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.Console;

public class BoardController {
    
    Scanner keyScan;
    
    BoardDao boardDao = new BoardDao();
    
    public BoardController(Scanner scanner) {
        this.keyScan = scanner;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("board/add")) {
            onBoardAdd();
        } else if (menu.equals("board/list")) {
            onBoardList();
        } else if (menu.equals("board/view")) {
            onBoardView(option);
        } else if (menu.equals("board/update")) {
            onBoardUpdate(option);
        } else if (menu.equals("board/delete")) {
            onBoardDelete(option);
        }
    }
    
    void onBoardAdd() {
        System.out.println("[게시글 정보 입력]");
        
        Board board = new Board();
        System.out.print("제목? ");
        board.title = keyScan.nextLine();
        
        System.out.print("내용? ");
        board.content = keyScan.nextLine();
        
        System.out.print("등록일? ");
        board.createDate = Date.valueOf(keyScan.nextLine());
        
        boardDao.insert(board);
    }
    
    void onBoardList() {
        System.out.println("[게시판 리스트]");
        Board[] list = boardDao.list();
        for (int i = 0; i < list.length;  i++) {
            if (list[i] == null) continue;
            System.out.printf("%d, %s, %s\n",
                i, list[i].title, list[i].createDate);
        }
    }
    
    void onBoardView(String option) {
        System.out.println("[게시글 조회]");
        
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        Board board = boardDao.get(Integer.parseInt(option));
        
        if (board == null) {
            System.out.println("유효하지 않은 게시물 번호입니다.");
        } else {
            System.out.printf("제목: %s\n", board.title);
            System.out.printf("내용: %s\n", board.content);
            System.out.printf("등록일: %s\n", board.createDate);
        }
    }
    
    void onBoardUpdate(String option) {
        System.out.println("[게시글 정보 수정]");
        
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        Board board = boardDao.get(Integer.parseInt(option));
        
        if (board == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Board updateTeam = new Board();
            System.out.printf("제목(%s)? ", board.title);
            updateTeam.title = keyScan.nextLine();
            System.out.printf("내용(%s)? ", board.content);
            updateTeam.content = keyScan.nextLine();
            System.out.printf("등록일(%s)? ", board.createDate);
            updateTeam.createDate = Date.valueOf(keyScan.nextLine());
            boardDao.update(updateTeam);
            System.out.println("변경하였습니다.");
        }
    }
    
    void onBoardDelete(String option) {
        System.out.println("[게시글 정보 삭제]");
        
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        int i = Integer.parseInt(option);
        Board board = boardDao.get(i);
        
        if (board == null) {
            System.out.println("유효하지 않는 게시물 번호입니다.");
        } else {
            Console.confirm("정말 삭제하시겠습니까?");
            boardDao.delete(i);
            System.out.println("삭제하였습니다.");
        }
    }
    
}
