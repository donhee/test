package bitcamp.java106.pms.controller.team;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Team;

@Component("team/add")
public class TeamAddController implements Controller  {
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamAddController(Scanner scanner, TeamDao teamDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[팀 정보 입력]");
        
        Team team = new Team();
        
        System.out.print("팀명? ");
        team.setName(this.keyScan.nextLine());
        
        System.out.print("설명? ");
        team.setDescription(this.keyScan.nextLine());
        
        System.out.print("최대인원? ");
        team.setMaxQty(this.keyScan.nextInt());
        keyScan.nextLine();
        
        System.out.print("시작일? ");
        team.setStartDate(Date.valueOf(this.keyScan.nextLine()));
        
        System.out.print("종료일? ");
        team.setEndDate(Date.valueOf(this.keyScan.nextLine()));
        
        teamDao.insert(team);
    }
    
}
