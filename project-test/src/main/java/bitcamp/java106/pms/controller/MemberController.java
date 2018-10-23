package bitcamp.java106.pms.controller;

import java.util.Scanner;

import bitcamp.java106.pms.Dao.MemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.util.Console;

public class MemberController {
    Scanner keyScan;
    
    MemberDao memberDao = new MemberDao();
    
    public MemberController(Scanner scanner) {
        this.keyScan = scanner;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("member/add")) {
            onMemberAdd();
        } else if (menu.equals("member/list")) {
            onMemberList();
        } else if (menu.equals("member/view")) {
            onMemberView(option);
        } else if (menu.equals("member/update")) {
            onMemberUpdate(option);
        } else if (menu.equals("member/delete")) {
            onMemberDelete(option);
        }
    }
    
    void onMemberAdd() {
        System.out.println("[회원 정보 입력]");
        
        Member member = new Member();
        System.out.print("아이디? ");
        member.id = keyScan.nextLine();
        
        System.out.print("이메일? ");
        member.email = keyScan.nextLine();
        
        System.out.print("암호? ");
        member.password = keyScan.nextLine();
        
        memberDao.insert(member);
    }
    
    void onMemberList() {
        System.out.println("[회원 리스트]");
        Member[] list = memberDao.list();
        for (int i = 0; i < list.length;  i++) {
            if (list[i] == null) 
                continue;
            System.out.printf("%s, %s, %s\n",
                    list[i].id, list[i].email, list[i].password);
        }
    }
    
    void onMemberView(String id) {
        System.out.println("[회원 정보 조회]");
        
        if (id == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        Member member = memberDao.get(id);
        
        if (member == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            System.out.printf("아이디: %s\n", member.id);
            System.out.printf("이메일: %s\n", member.email);
            System.out.printf("암호: %s\n", member.password);
        }
    }
    
    void onMemberUpdate(String id) {
        System.out.println("[회원 정보 수정]");
        
        if (id == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        Member member = memberDao.get(id);
        
        if (member == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Member updateMember = new Member();
            System.out.printf("아이디 : %s\n", member.id);
            updateMember.id = member.id;
            System.out.printf("이메일(%s)? ", member.email);
            updateMember.email = keyScan.nextLine();
            System.out.printf("암호(%s)? ", member.password);
            updateMember.password = keyScan.nextLine();

            memberDao.update(updateMember);
            System.out.println("변경하였습니다.");
        }
    }
    
    void onMemberDelete(String id) {
        System.out.println("[회원 정보 삭제]");
        
        if (id == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        Member member = memberDao.get(id);

        if (member == null) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Console.confirm("정말 삭제하시겠습니까?");

            memberDao.delete(id);
            System.out.println("삭제하였습니다.");
        }
    }
    
}
