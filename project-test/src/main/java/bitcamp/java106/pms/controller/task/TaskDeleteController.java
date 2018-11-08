package bitcamp.java106.pms.controller.task;

import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.MemberDao;
import bitcamp.java106.pms.dao.TaskDao;
import bitcamp.java106.pms.dao.TeamDao;
import bitcamp.java106.pms.dao.TeamMemberDao;
import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.Console;

@Component("task/delete")
public class TaskDeleteController implements Controller  {
    
    Scanner keyScan;
    TeamDao teamDao;
    TaskDao taskDao;
    MemberDao memberDao;
    TeamMemberDao teamMemberDao;
    
    public TaskDeleteController(Scanner scanner, TeamDao teamDao, 
            TaskDao taskDao, TeamMemberDao teamMemberDao, MemberDao memberDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
        this.taskDao = taskDao;
        this.teamMemberDao = teamMemberDao;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        Team team = teamDao.get(option);
        if (team == null) {
            System.out.printf("'%s' 팀은 존재하지 않습니다.", option);
        }
        
        System.out.println("[팀 작업 삭제]");
        System.out.print("삭제할 작업의 번호? ");
        int taskNo = Integer.parseInt(keyScan.nextLine());
        
        Task task = taskDao.get(taskNo);
        if (task == null) {
            System.out.printf("'%s'팀의 %d번 작업을 찾을 수 없습니다.\n",
                    team.getName(), taskNo);
            return;
        }
        
        if (Console.confirm("삭제하시겠습니까?")) {
            taskDao.delete(task.getNo());
            System.out.println("삭제하였습니다.");
        } else {
            System.out.println("취소하였습니다.");
        }
    }
}