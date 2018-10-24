package bitcamp.java106.pms;

import java.util.Scanner;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;


public class App {
    // 클래스 변수 = 스태틱 변수 
    // => 클래스 안에서 어디에서나 사용할 수 있는 변수이다.
    static Scanner keyScan = new Scanner(System.in);

    static Team[] teams = new Team[100];
    static int teamIndex = 0;
    static String option = null;
    static Member[] members = new Member[100];
    static int memberIndex = 0;
    
    static String[] prompt() {
        System.out.print("명령> ");
        return keyScan.nextLine().toLowerCase().split(" ");
    }
    
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
    }
    
    static void onTeamAdd() {
        System.out.println("[팀 정보 입력]");
        
        Team team = new Team();
        
        System.out.print("팀명? ");
        team.name = keyScan.nextLine();
        
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
    }
    
    static void onTeamList() {
        System.out.println("[팀 목록]");
        
        for (int i = 0; i < teamIndex;  i++) {
            if (teams[i] == null) continue;
            System.out.printf("%s, %d명, %s ~ %s\n",
                teams[i].name, teams[i].maxQty, 
                teams[i].startDate, teams[i].endDate);
        }
    }
    
    static void onTeamView() {
        System.out.println("[팀 정보 조회]");
        
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            System.out.println();
            return; // 값을 리턴하면 안되기 때문에 return 명령만 작성한다.
            // 의미는? 즉시 메서드 실행을 멈추고 이전 위치로 돌아간다.
        }
        
        Team team = null;
        for (int i = 0; i < teamIndex; i++) {
            if (option.equals(teams[i].name.toLowerCase())) {
                team = teams[i];
                break;
            }
        }
        
        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            System.out.printf("팀명: %s\n", team.name);
            System.out.printf("설명: %s\n", team.description);
            System.out.printf("최대인원: %d\n", team.maxQty);
            System.out.printf("기간: %s ~ %s\n", team.startDate, team.endDate);
        }
    }
    
    static void onTeamUpdate() {
        System.out.println("[팀 정보 변경]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        Team team = null;
        int i = 0;
        for (i = 0; i < teamIndex; i++) {
            if (option.equals(teams[i].name.toLowerCase())) {
                team = teams[i];
                break;
            }
        }
        
        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team updateTeam = new Team();
            
            System.out.printf("팀명(%s)? ", team.name);
            updateTeam.name = keyScan.nextLine();
            System.out.printf("설명(%s)? ", team.description);
            updateTeam.description = keyScan.nextLine();
            System.out.printf("최대인원(%d)? ", team.maxQty);
            updateTeam.maxQty = Integer.parseInt(keyScan.nextLine());
            System.out.printf("시작일(%s)? ", team.startDate);
            updateTeam.startDate = keyScan.nextLine();
            System.out.printf("시작일(%s)? ", team.startDate);
            updateTeam.endDate = keyScan.nextLine();
            
            teams[i] = updateTeam;
            
            System.out.println("변경하였습니다.");
        }
    }
    
    static void onTeamDelete() {
        System.out.println("[팀 정보 삭제]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        Team team = null;
        int i;
        for (i = 0; i < teamIndex; i++) {
            if (teams[i] == null) continue;
            if (option.equals(teams[i].name.toLowerCase())) {
                team = teams[i];
                break;
            }
        }
        
        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            System.out.print("정말 삭제하시겠습니까?(y/n) ");
            String input = keyScan.nextLine().toLowerCase();
            if (input.equals("y")) {
                teams[i] = null;
                System.out.println("삭제하였습니다.");
            }
        }
    }
    
    static void onMemberAdd() {
        System.out.println("[회원 정보 입력]");
        
        Member member = new Member();
        System.out.print("아이디? ");
        member.id = keyScan.nextLine();
        
        System.out.print("이메일? ");
        member.email = keyScan.nextLine();
        
        System.out.print("암호? ");
        member.password = keyScan.nextLine();
        
        members[memberIndex++] = member;
    }
    
    static void onMemberList() {
        System.out.println("[회원 리스트]");
        
        for (int i = 0; i < memberIndex;  i++) {
            if (members[i] == null) continue;
            System.out.printf("%s, %s, %s\n",
                    members[i].id, members[i].email, members[i].password);
        }
    }
    
    static void onMemberView() {
        System.out.println("[회원 정보 조회]");
        
        if (option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            System.out.println();
            return;
        }
        
        Member member = null;
        for (int i = 0; i < memberIndex; i++) {
            if (option.equals(members[i].id.toLowerCase())) {
                member = members[i];
                break;
            }
        }
        
        if (member == null) {
            System.out.println("해당 회원이 없습니다.");
        } else {
            System.out.printf("아이디: %s\n", member.id);
            System.out.printf("이메일: %s\n", member.email);
            System.out.printf("암호: %s\n", member.password);
        }
    }
    
    static void onMemberUpdate() {
        System.out.println("[회원 정보 변경]");
        if (option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        Member member = null;
        int i = 0;
        for (i = 0; i < memberIndex; i++) {
            if (option.equals(members[i].id.toLowerCase())) {
                member = members[i];
                break;
            }
        }
        
        if (member == null) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member updateMember = new Member();
            System.out.printf("아이디(%s)? ", member.id);
            updateMember.id = keyScan.nextLine();
            System.out.printf("이메일(%s)? ", member.email);
            updateMember.email = keyScan.nextLine();
            System.out.printf("암호(%s)? ", member.password);
            updateMember.password = keyScan.nextLine();
            
            members[i] = updateMember;
            System.out.println("변경하였습니다.");
        }
    }

    static void onMemberDelete() {
        System.out.println("[회원 정보 삭제]");
        if (option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        Member member = null;
        int i;
        for (i = 0; i < memberIndex; i++) {
            if (members[i] == null) continue;
            if (option.equals(members[i].id.toLowerCase())) {
                member = members[i];
                break;
            }
        }
        
        if (member == null) {
            System.out.println("해당 아이디의 회원이 않습니다.");
        } else {
            System.out.print("정말 삭제하시겠습니까?(y/N) ");
            String input = keyScan.nextLine();
            if (input.equals("y")) {
                members[i] = null;
                System.out.println("삭제하였습니다.");
            }
        }
    }
    
    public static void main(String[] args) {
        while (true) {
            String[] arr = prompt();
            
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
            } else if (menu.equals("team/add")) {
                onTeamAdd();
            } else if (menu.equals("team/list")) {
                onTeamList();
            } else if (menu.equals("team/view")) {
                onTeamView();
            } else if (menu.equals("team/update")) {
                onTeamUpdate();
            } else if (menu.equals("team/delete")) {
                onTeamDelete();
            } else if (menu.equals("member/add")) {
                onMemberAdd();
            } else if (menu.equals("member/list")) {
                onMemberList();
            } else if (menu.equals("member/view")) {
                onMemberView();
            } else if (menu.equals("member/update")) {
                onMemberUpdate();
            } else if (menu.equals("member/delete")) {
                onMemberDelete();
            } else {
                System.out.println("명령어가 올바르지 않습니다.");
            }
            
            System.out.println();
        }
    
    }
    
}