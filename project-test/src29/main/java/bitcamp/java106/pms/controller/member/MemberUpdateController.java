package bitcamp.java106.pms.controller.member;

import java.io.PrintWriter;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component("/member/update")
public class MemberUpdateController implements Controller  {
    MemberDao memberDao;
    
    public MemberUpdateController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    public void service(ServerRequest request, ServerResponse response) {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        
        Member member = memberDao.get(id);
        
        if (member == null) {
            out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member updateMember = new Member();
            updateMember.setId(id);
            updateMember.setEmail(request.getParameter("email"));
            updateMember.setPassword(request.getParameter("password"));
            
            int index = memberDao.indexOf(id);
            memberDao.update(index, updateMember);
            out.println("변경하였습니다.");
        }
    }
    
}    
