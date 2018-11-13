package bitcamp.java106.pms.controller.team;

import java.io.PrintWriter;
import java.util.Iterator;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.server.ServerRequest;
import bitcamp.java106.pms.server.ServerResponse;

@Component("/team/list")
public class TeamListController implements Controller  {
    TeamDao teamDao;
    
    public TeamListController(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
    
    public void service(ServerRequest request, ServerResponse response) {
        PrintWriter out = response.getWriter();
        
        Iterator<Team> iterator = teamDao.list();
        while (iterator.hasNext()) {
            Team team = iterator.next();
            out.printf("%s, %d명, %s ~ %s\n",
                    team.getName(), team.getMaxQty(), 
                    team.getStartDate(), team.getEndDate());
        }
    }
    
}
