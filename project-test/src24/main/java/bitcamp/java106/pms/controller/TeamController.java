package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.Console;

@Component("team")
public class TeamController implements Controller  {
    Scanner keyScan;
    TeamDao teamDao;
    
    public TeamController(Scanner scanner, TeamDao teamDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("team/add")) {
            this.onTeamAdd();
        } else if (menu.equals("team/list")) {
            this.onTeamList();
        } else if (menu.equals("team/view")) {
            this.onTeamView(option);
        } else if (menu.equals("team/update")) {
            this.onTeamUpdate(option);
        } else if (menu.equals("team/delete")) {
            this.onTeamDelete(option);
        }  else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    void onTeamAdd() {
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
    
    void onTeamList() {
        System.out.println("[팀 목록]");
        
        Iterator<Team> iterator = teamDao.list();
        while (iterator.hasNext()) {
            Team team = iterator.next();
            System.out.printf("%s, %d명, %s ~ %s\n",
                    team.getName(), team.getMaxQty(), 
                    team.getStartDate(), team.getEndDate());
        }
    }
    
    void onTeamView(String name) {
        System.out.println("[팀 정보 조회]");
        
        if (name == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; // 값을 리턴하면 안되기 때문에 return 명령만 작성한다.
            // 의미는? 즉시 메서드 실행을 멈추고 이전 위치로 돌아간다.
        }
        
        Team team = teamDao.get(name);
        
        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            System.out.printf("팀명: %s\n", team.getName());
            System.out.printf("설명: %s\n", team.getDescription());
            System.out.printf("최대인원: %d\n", team.getMaxQty());
            System.out.printf("기간: %s ~ %s\n", team.getStartDate(), team.getEndDate());
        }
    }
    
    void onTeamUpdate(String name) {
        System.out.println("[팀 정보 변경]");
        if (name == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        Team team = teamDao.get(name);
        
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
    
    void onTeamDelete(String name) {
        System.out.println("[팀 정보 삭제]");
        if (name == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        Team team = teamDao.get(name);
        
        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            if (Console.confirm("정말 삭제하시겠습니까?")) {
                teamDao.delete(name);
                System.out.println("삭제하였습니다.");
            }
        }
    }
    
}
