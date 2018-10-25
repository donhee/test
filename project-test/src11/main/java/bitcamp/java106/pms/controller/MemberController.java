package bitcamp.java106.pms.controller;

import java.util.Scanner;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.util.Console;

public class MemberController {
    // 이 클래스를 사용하기 전에 App 클래스에서 준비한 Scanner 객체를
    // keyScan 변수에 저장하라!
    public Scanner keyScan;
    
    Member[] members = new Member[100];
    int memberIndex = 0;
    
    public void service(String menu, String option) {
        if (menu.equals("member/add")) {
            this.onMemberAdd();
        } else if (menu.equals("member/list")) {
            this.onMemberList();
        } else if (menu.equals("member/view")) {
            this.onMemberView(option);
        } else if (menu.equals("member/update")) {
            this.onMemberUpdate(option);
        } else if (menu.equals("member/delete")) {
            this.onMemberDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    int getMemberIndex(String id) {
        for (int i = 0; i < this.memberIndex; i++) {
            if (this.members[i] == null) continue;
            if (id.equals(this.members[i].id.toLowerCase())) {
                return i;
            }
        }
        return -1;
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
        
        this.members[this.memberIndex++] = member;
    }
    
    void onMemberList() {
        System.out.println("[회원 리스트]");
        
        for (int i = 0; i < this.memberIndex;  i++) {
            if (this.members[i] == null) continue;
            System.out.printf("%s, %s, %s\n",
                    this.members[i].id, this.members[i].email, this.members[i].password);
        }
    }
    
    void onMemberView(String id) {
        System.out.println("[회원 정보 조회]");
        
        if (id == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        int i = getMemberIndex(id);
        
        if (i == -1) {
            System.out.println("해당 회원이 없습니다.");
        } else {
            Member member = this.members[i];
            System.out.printf("아이디: %s\n", member.id);
            System.out.printf("이메일: %s\n", member.email);
            System.out.printf("암호: %s\n", member.password);
        }
    }
    
    void onMemberUpdate(String id) {
        System.out.println("[회원 정보 변경]");
        if (id == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        int i = getMemberIndex(id);
        
        if (i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member member = this.members[i];
            Member updateMember = new Member();
            System.out.printf("아이디(%s)? ", member.id);
            updateMember.id = keyScan.nextLine();
            System.out.printf("이메일(%s)? ", member.email);
            updateMember.email = keyScan.nextLine();
            System.out.printf("암호(%s)? ", member.password);
            updateMember.password = keyScan.nextLine();
            
            this.members[i] = updateMember;
            System.out.println("변경하였습니다.");
        }
    }

    void onMemberDelete(String id) {
        System.out.println("[회원 정보 삭제]");
        if (id == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        int i = getMemberIndex(id);
        
        if (i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            if (Console.confirm("정말 삭제하시겠습니까?")) {
                this.members[i] = null;
                System.out.println("삭제하였습니다.");
            }
        }
    }
    
}
