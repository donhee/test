package bitcamp.java106.pms;

import java.util.Scanner;

import bitcamp.java106.pms.controller.BoardController;
import bitcamp.java106.pms.controller.MemberController;
import bitcamp.java106.pms.controller.TeamController;
import bitcamp.java106.pms.util.Console;
// ver 14 - 다시 해볼것 !! 안보고!!! 제대로 이해하고 넘어가자!!!! 
// 18-04-02에 ver14까지 완료했지만
// 다시 복습할겄!!!!!!!!!!!!!!!!!!
public class App {
    static Scanner keyScan = new Scanner(System.in);
    
    public static String option = null;
    
    static void onQuit() {
        System.out.println("quit! 안녕히 가세요!");
    }
    
    static void onHelp() {
        System.out.println("[도움말]");
        System.out.println("팀 등록 명령 : team/add");
        System.out.println("팀 조회 명령 : team/list");
        System.out.println("팀 상세저회 명령 : team/view 팀명");
        System.out.println("회원 등록 명령 : member/add");
        System.out.println("회원 조회 명령 : member/list");
        System.out.println("회원 상세조회 명령 : member/view 아이디");
        System.out.println("종료 : quit");
        System.out.println();
    }
    
    public static void main(String[] args) {
        TeamController teamController = new TeamController(keyScan);
        MemberController memberController = new MemberController(keyScan);
        BoardController boardController = new BoardController(keyScan);
        
        Console.keyScan = keyScan;
        
        while (true) {
            String[] arr = Console.prompt();
            
            String menu = arr[0];
            if (arr.length == 2) {
                option = arr[1];
            } else {
                option = null;
            }
            
            if (menu.equals("quit")) {
                onQuit();
                break;
            } else if (menu.equals("help")) {
                onHelp();
            } else if (menu.startsWith("team/")) {
                teamController.service(menu, option);
            } else if (menu.startsWith("member/")) {
                memberController.service(menu, option);
            } else if (menu.startsWith("board/")) {
                boardController.service(menu, option);
            } else {
                System.out.println("명령어가 올바르지 않습니다.");
            }
            
            System.out.println();
        }
    
    }
}