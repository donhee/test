package bitcamp.java106.pms.controller.member;

import java.io.PrintWriter;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component("/member/view")
public class MemberViewController implements Controller  {
    MemberDao memberDao;
    
    public MemberViewController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    public void service(ServerRequest request, ServerResponse response) {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        
        Member member = memberDao.get(id);
        
        if (member == null) {
            out.println("해당 회원이 없습니다.");
        } else {
            out.printf("아이디: %s\n", member.getId());
            out.printf("이메일: %s\n", member.getEmail());
            out.printf("암호: %s\n", member.getPassword());
        }
    }
    
}
