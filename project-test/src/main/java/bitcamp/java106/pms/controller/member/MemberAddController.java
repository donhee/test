package bitcamp.java106.pms.controller.member;

import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;

@Component("member/add")
public class MemberAddController implements Controller  {
    Scanner keyScan;
    MemberDao memberDao;
    
    public MemberAddController(Scanner scanner, MemberDao memberDao) {
        this.keyScan = scanner;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[회원 정보 입력]");
        
        Member member = new Member();
        System.out.print("아이디? ");
        member.setId(keyScan.nextLine());
        
        System.out.print("이메일? ");
        member.setEmail(keyScan.nextLine());
        
        System.out.print("암호? ");
        member.setPassword(keyScan.nextLine());
        
        memberDao.insert(member);
    }
    
}
