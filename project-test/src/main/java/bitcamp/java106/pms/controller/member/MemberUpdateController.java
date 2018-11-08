package bitcamp.java106.pms.controller.member;

import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;

@Component("member/update")
public class MemberUpdateController implements Controller  {
    Scanner keyScan;
    MemberDao memberDao;
    
    public MemberUpdateController(Scanner scanner, MemberDao memberDao) {
        this.keyScan = scanner;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[회원 정보 변경]");
        if (option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        Member member = memberDao.get(option);
        
        if (member == null) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member updateMember = new Member();
            System.out.printf("아이디 : (%s)\n", member.getId());
            updateMember.setId(member.getId());
            System.out.printf("이메일(%s)? ", member.getEmail());
            updateMember.setEmail(keyScan.nextLine());
            System.out.printf("암호(%s)? ", member.getPassword());
            updateMember.setPassword(keyScan.nextLine());
            
            int index = memberDao.indexOf(member.getId());
            memberDao.update(index, updateMember);
            System.out.println("변경하였습니다.");
        }
    }
    
}    
