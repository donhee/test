package bitcamp.java106.pms.controller.team;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Team;

@Component("team/update")
public class TeamUpdateController implements Controller  {
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamUpdateController(Scanner scanner, TeamDao teamDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[팀 정보 변경]");
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        Team team = teamDao.get(option);
        
        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team updateTeam = new Team();
            System.out.printf("팀명 : (%s)\n", team.getName());
            updateTeam.setName(team.getName());
            System.out.printf("설명(%s)? ", team.getDescription());
            updateTeam.setDescription(keyScan.nextLine());
            System.out.printf("최대인원(%d)? ", team.getMaxQty());
            updateTeam.setMaxQty(Integer.parseInt(keyScan.nextLine()));
            System.out.printf("시작일(%s)? ", team.getStartDate());
            updateTeam.setStartDate(Date.valueOf(keyScan.nextLine()));
            System.out.printf("시작일(%s)? ", team.getStartDate());
            updateTeam.setEndDate(Date.valueOf(keyScan.nextLine()));
            
            int index = teamDao.indexOf(updateTeam.getName());
            teamDao.update(index, updateTeam);
            
            System.out.println("변경하였습니다.");
        }
    }
    
}
