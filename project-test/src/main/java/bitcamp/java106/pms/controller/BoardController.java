package bitcamp.java106.pms.controller;

import java.util.Scanner;

import bitcamp.java106.pms.domain.Board;
import bitcamp.java106.pms.util.Console;

public class BoardController {
    // 이 클래스를 사용하기 전에 App 클래스에서 준비한 Scanner 객체를
    // keyScan 변수에 저장하라!
    public static Scanner keyScan;
    
    static Board[] boards = new Board[100];
    static int boardIndex = 0;
    
    public static void service(String menu, String option) {
        if (menu.equals("board/add")) {
            BoardController.onBoardAdd();
        } else if (menu.equals("board/list")) {
            BoardController.onBoardList();
        } else if (menu.equals("board/view")) {
            BoardController.onBoardView(option);
        } else if (menu.equals("board/update")) {
            BoardController.onBoardUpdate(option);
        } else if (menu.equals("board/delete")) {
            BoardController.onBoardDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    public static void onBoardAdd() {
        System.out.println("[게시물 입력]");
        
        Board board = new Board();

        System.out.print("제목? ");
        board.title = keyScan.nextLine();
        
        System.out.print("내용? ");
        board.content = keyScan.nextLine();
        
        System.out.print("등록일? ");
        board.createDate = keyScan.nextLine();
        
        boards[boardIndex++] = board;
    }
    
    public static void onBoardList() {
        System.out.println("[게시물 목록]");
        
        for (int i = 0; i < boardIndex;  i++) {
            if (boards[i] == null) continue;
            System.out.printf("%d, %s, %s\n",
                    i, boards[i].title, boards[i].createDate);
        }
    }
    
    public static void onBoardView(String option) {
        System.out.println("[게시물 조회]");
        
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        int i = Integer.parseInt(option);
        
        if (i < 0 || i >= boardIndex) {
            System.out.println("해당 게시물 번호가 없습니다.");
        } else {
            Board board = boards[i];
            System.out.printf("제목: %s\n", board.title);
            System.out.printf("내용: %s\n", board.content);
            System.out.printf("시작일: %s\n", board.createDate);
        }
    }
    
    public static void onBoardUpdate(String option) {
        System.out.println("[게시물 변경]");
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        int i = Integer.parseInt(option);
        
        if (i < 0 || i >= boardIndex) {
            System.out.println("해당 게시물 번호가 없습니다.");
        } else {
            Board board = boards[i];
            Board updateBoard = new Board();
            System.out.printf("제목(%s)? ", board.title);
            updateBoard.title = keyScan.nextLine();
            System.out.printf("내용(%s)? ", board.content);
            updateBoard.content = keyScan.nextLine();
            System.out.printf("시작일(%s)? ", board.createDate);
            updateBoard.createDate = keyScan.nextLine();
            
            boards[i] = updateBoard;
            System.out.println("변경하였습니다.");
        }
    }

    public static void onBoardDelete(String option) {
        System.out.println("[게시물 삭제]");
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        int i = Integer.parseInt(option);
        
        if (i == -1) {
            System.out.println("해당 게시물이 없습니다.");
        } else {
            if (Console.confirm("정말 삭제하시겠습니까?")) {
                boards[i] = null;
                System.out.println("삭제하였습니다.");
            }
        }
    }
    
}
