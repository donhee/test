package bitcamp.java106.pms.controller.teammember;

import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.dao.TeamMemberDao;
import bitcamp.java106.pms.domain.Team;

@Component("team/member/list")
public class TeamMemberListController implements Controller  {
    Scanner keyScan;
    TeamDao teamDao;
    MemberDao memberDao;
    TeamMemberDao teamMemberDao;
    
    public TeamMemberListController(Scanner scanner, TeamDao teamDao, MemberDao memberDao, TeamMemberDao teamMemberDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
        this.memberDao = memberDao;
        this.teamMemberDao = teamMemberDao;
    }
    
    public void service(String menu, String teamName) {
        if (teamName == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        Team team = teamDao.get(teamName);
        if (team == null) {
            System.out.printf("%s 팀은 존재하지 않습니다.", teamName);
            return;
        }
        
        System.out.println("[팀 멤버 목록]");
        System.out.print("회원들: ");
        
        Iterator<String> iterator = teamMemberDao.getMembers(teamName);
        if (iterator != null) {
            while (iterator.hasNext()) {
                System.out.printf("%s, ", iterator.next());
            }
        }
        System.out.println();
    }
    
}
