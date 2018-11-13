package bitcamp.java106.pms.controller.teammember;

import java.io.PrintWriter;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.dao.TeamMemberDao;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component("/team/member/delete")
public class TeamMemberDeleteController implements Controller  {
    TeamDao teamDao;
    MemberDao memberDao;
    TeamMemberDao teamMemberDao;
    
    public TeamMemberDeleteController(TeamDao teamDao, MemberDao memberDao, TeamMemberDao teamMemberDao) {
        this.teamDao = teamDao;
        this.memberDao = memberDao;
        this.teamMemberDao = teamMemberDao;
    }
    
    public void service(ServerRequest request, ServerResponse response) {
        PrintWriter out = response.getWriter();
        String teamName = request.getParameter("teamName");
        
        Team team = teamDao.get(teamName);
        if (team == null) {
            out.printf("%s 팀은 존재하지 않습니다.", teamName);
            return;
        }
        
        String memberId = request.getParameter("memberId");
        
        if (!teamMemberDao.isExist(teamName, memberId)) {
            out.println("이 팀의 회원이 아닙니다.");
            return;
        }
        
        teamMemberDao.deleteMember(teamName, memberId);
        out.println("팀에서 회원을 삭제하였습니다.");
    }
    
}
