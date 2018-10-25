package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.dao.BoardDao;
import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.Console;

public class BoardController {
    // 이 클래스를 사용하기 전에 App 클래스에서 준비한 Scanner 객체를
    // keyScan 변수에 저장하라!
    Scanner keyScan;
    BoardDao boardDao = new BoardDao();
    
    public BoardController(Scanner scanner) {
        this.keyScan = scanner;
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
        board.title = keyScan.nextLine();
        
        System.out.print("내용? ");
        board.content = keyScan.nextLine();
        
        System.out.print("등록일? ");
        board.createDate = Date.valueOf(keyScan.nextLine());
        
        boardDao.insert(board);
    }
    
    void onBoardList() {
        System.out.println("[게시물 목록]");
        
        Board[] list = boardDao.list();
        
        for (int i = 0; i < list.length;  i++) {
            if (list[i] == null) continue;
            System.out.printf("%d, %s, %s\n",
                    i, list[i].title, list[i].createDate);
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
            System.out.printf("제목: %s\n", board.title);
            System.out.printf("내용: %s\n", board.content);
            System.out.printf("시작일: %s\n", board.createDate);
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
            System.out.printf("제목(%s)? ", board.title);
            updateBoard.title = keyScan.nextLine();
            System.out.printf("내용(%s)? ", board.content);
            updateBoard.content = keyScan.nextLine();
            System.out.printf("시작일(%s)? ", board.createDate);
            updateBoard.createDate = Date.valueOf(keyScan.nextLine());
            updateBoard.no = board.no;
            //this.boards[i] = updateBoard;
            boardDao.update(updateBoard);
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
