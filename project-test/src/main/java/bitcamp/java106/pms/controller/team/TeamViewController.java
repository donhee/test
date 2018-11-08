package bitcamp.java106.pms.controller.team;

import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Team;

@Component("team/view")
public class TeamViewController implements Controller  {
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamViewController(Scanner scanner, TeamDao teamDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[팀 정보 조회]");
        
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; // 값을 리턴하면 안되기 때문에 return 명령만 작성한다.
            // 의미는? 즉시 메서드 실행을 멈추고 이전 위치로 돌아간다.
        }
        
        Team team = teamDao.get(option);
        
        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            System.out.printf("팀명: %s\n", team.getName());
            System.out.printf("설명: %s\n", team.getDescription());
            System.out.printf("최대인원: %d\n", team.getMaxQty());
            System.out.printf("기간: %s ~ %s\n", team.getStartDate(), team.getEndDate());
        }
    }
    
}
