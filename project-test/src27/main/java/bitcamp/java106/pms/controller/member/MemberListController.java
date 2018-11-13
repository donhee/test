package bitcamp.java106.pms.controller.member;

import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;

@Component("member/list")
public class MemberListController implements Controller  {
    Scanner keyScan;
    MemberDao memberDao;
    
    public MemberListController(Scanner scanner, MemberDao memberDao) {
        this.keyScan = scanner;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[회원 리스트]");
        
        Iterator<Member> iterator = memberDao.list();
        while (iterator.hasNext()) {
            Member member = iterator.next();
            System.out.printf("%s, %s, %s\n",
                    member.getId(), member.getEmail(), member.getPassword());
        }
    }
    
}
