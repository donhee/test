package bitcamp.java106.pms.controller.team;

import java.io.PrintWriter;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component("/team/view")
public class TeamViewController implements Controller  {
    TeamDao teamDao;
    
    public TeamViewController(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
    
    public void service(ServerRequest request, ServerResponse response) {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        
        Team team = teamDao.get(name);
        
        if (team == null) {
            out.println("해당 이름의 팀이 없습니다.");
        } else {
            out.printf("팀명: %s\n", team.getName());
            out.printf("설명: %s\n", team.getDescription());
            out.printf("최대인원: %d\n", team.getMaxQty());
            out.printf("기간: %s ~ %s\n", team.getStartDate(), team.getEndDate());
        }
    }
    
}
