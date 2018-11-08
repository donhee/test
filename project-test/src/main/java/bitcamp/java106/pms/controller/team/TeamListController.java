package bitcamp.java106.pms.controller.team;

import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Team;

@Component("team/list")
public class TeamListController implements Controller  {
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamListController(Scanner scanner, TeamDao teamDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[팀 목록]");
        
        Iterator<Team> iterator = teamDao.list();
        while (iterator.hasNext()) {
            Team team = iterator.next();
            System.out.printf("%s, %d명, %s ~ %s\n",
                    team.getName(), team.getMaxQty(), 
                    team.getStartDate(), team.getEndDate());
        }
    }
    
}
