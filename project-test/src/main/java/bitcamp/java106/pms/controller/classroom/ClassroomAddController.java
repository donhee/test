package bitcamp.java106.pms.controller.classroom;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassroomDao;
import bitcamp.java106.pms.domain.Classroom;

@Component("classroom/add")
public class ClassroomAddController implements Controller  {
    Scanner keyScan;
    ClassroomDao classroomDao;
    
    public ClassroomAddController(Scanner scanner, ClassroomDao classroomDao) {
        this.keyScan = scanner;
        this.classroomDao = classroomDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[수업 등록]");
        
        Classroom classroom = new Classroom();

        System.out.print("수업명? ");
        classroom.setTitle(keyScan.nextLine());
        
        System.out.print("시작일? ");
        classroom.setStartDate(Date.valueOf(keyScan.nextLine()));
        
        System.out.print("종료일? ");
        classroom.setEndDate(Date.valueOf(keyScan.nextLine()));
        
        System.out.print("교실번호? ");
        classroom.setRoom(keyScan.nextLine());
        
        classroomDao.insert(classroom);
    }
    
}
