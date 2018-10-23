package bitcamp.java106.pms;

import java.util.Scanner;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;


public class App {
    // 클래스 변수 = 스태틱 변수
    // => 클래스 안에서 어디에서나 사용할 수 있는 변수이다.
    static Scanner keyScan = new Scanner(System.in);

    static String[] prompt() {
        System.out.print("명령> ");
        return keyScan.nextLine().toLowerCase().split(" ");
    }
    
    public static void main(String[] args) {
        
        Team[] teams = new Team[100];
        int teamIndex = 0;
        
        Member[] members = new Member[100];
        int memberIndex = 0;
        
        while (true) {
            
            String[] arr = prompt();
            
            String menu = arr[0];
            String option = null;
            if (arr.length == 2) {
                option = arr[1];
            } 
            
            if (menu.equals("quit")) {
                System.out.println("quit! 안녕히 가세요!");
                break;
            } else if (menu.equals("help")) {
                System.out.println("팀 등록 명령 : team/add");
                System.out.println("팀 조회 명령 : team/list");
                System.out.println("팀 상세저회 명령 : team/view 팀명");
                System.out.println("회원 등록 명령 : member/add");
                System.out.println("회원 조회 명령 : member/list");
                System.out.println("회원 상세조회 명령 : member/view 아이디");
                System.out.println("종료 : quit");
                System.out.println();
                continue;
            } else if (menu.equals("team/add")) {
                System.out.println("[팀 정보 입력]");
                
                Team team = new Team();
                System.out.print("팀명? ");
                team.teamName = keyScan.nextLine();
                
                System.out.print("설명? ");
                team.description = keyScan.nextLine();
                
                System.out.print("최대인원? ");
                team.maxQty = keyScan.nextInt();
                keyScan.nextLine();
                
                System.out.print("시작일? ");
                team.startDate = keyScan.nextLine();
                
                System.out.print("종료일? ");
                team.endDate = keyScan.nextLine();
                
                teams[teamIndex++] = team;
                
                System.out.println();
                continue;
                
            } else if (menu.equals("team/list")) {
                System.out.println("[팀 리스트]");
                
                for (int i = 0; i < teamIndex;  i++) {
                    
                System.out.printf("%s, %d명, %s ~ %s\n",
                        teams[i].teamName, teams[i].maxQty, teams[i].startDate, teams[i].endDate);
                }
                System.out.println();
                continue;
                
            } else if (menu.equals("team/view")) {
                System.out.println("[팀 정보 조회]");
                
                if (option == null) {
                    System.out.println("팀명을 입력하시기 바랍니다.");
                    System.out.println();
                    continue;
                }
                
                Team team = null;
                for (int i = 0; i < teamIndex; i++) {
                    if (option.equals(teams[i].teamName.toLowerCase())) {
                        team = teams[i];
                        break;
                    }
                }
                
                if (team == null) {
                    System.out.println("해당 이름의 팀이 없습니다.");
                } else {
                    System.out.printf("팀명: %s\n", team.teamName);
                    System.out.printf("설명: %s\n", team.description);
                    System.out.printf("최대인원: %d\n", team.maxQty);
                    System.out.printf("기간: %s ~ %s\n", team.startDate, team.endDate);
                }
                
            } else if (menu.equals("member/add")) {
                System.out.println("[회원 정보 입력]");
                
                Member member = new Member();
                System.out.print("아이디? ");
                member.id = keyScan.nextLine();
                
                System.out.print("이메일? ");
                member.email = keyScan.nextLine();
                
                System.out.print("암호? ");
                member.password = keyScan.nextLine();
                
                members[memberIndex++] = member;
                
            } else if (menu.equals("member/list")) {
                System.out.println("[회원 리스트]");
                
                for (int i = 0; i < memberIndex;  i++) {
                    
                System.out.printf("%s, %s, %s\n",
                        members[i].id, members[i].email, members[i].password);
                }
                System.out.println();
                continue;
                
            } else if (menu.equals("member/view")) {
                System.out.println("[회원 정보 조회]");
                
                if (option == null) {
                    System.out.println("아이디를 입력하시기 바랍니다.");
                    System.out.println();
                    continue;
                }
                
                Member member = null;
                for (int i = 0; i < memberIndex; i++) {
                    if (option.equals(members[i].id.toLowerCase())) {
                        member = members[i];
                        break;
                    }
                }
                
                if (member == null) {
                    System.out.println("해당 이름의 팀이 없습니다.");
                } else {
                    System.out.printf("아이디: %s\n", member.id);
                    System.out.printf("이메일: %s\n", member.email);
                    System.out.printf("암호: %s\n", member.password);
                }
                
            } else {
                System.out.println("명령어가 올바르지 않습니다.");
            }
            
            System.out.println();
        }
    
    }
}