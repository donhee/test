package bitcamp.java106.pms.controller.classroom;

import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.controller.Controller;
import bitcamp.java106.pms.dao.ClassroomDao;
import bitcamp.java106.pms.domain.Classroom;

@Component("classroom/view")
public class ClassroomViewController implements Controller  {
    Scanner keyScan;
    ClassroomDao classroomDao;
    
    public ClassroomViewController(Scanner scanner, ClassroomDao classroomDao) {
        this.keyScan = scanner;
        this.classroomDao = classroomDao;
    }
    
    public void service(String menu, String option) {
        System.out.println("[수업 조회]");
        
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        Classroom classroom = classroomDao.get(Integer.parseInt(option));
        
        if (classroom == null) {
            System.out.println("해당 게시물 번호가 없습니다.");
        } else {
            System.out.printf("제목: %s\n", classroom.getTitle());
            System.out.printf("시작일: %s\n", classroom.getStartDate());
            System.out.printf("종료일: %s\n", classroom.getEndDate());
            System.out.printf("교실번호: %s\n", classroom.getRoom());
        }
    }
    
}
