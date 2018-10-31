package bitcamp.java106.pms.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms.dao.ClassroomDao;
import bitcamp.java106.pms.domain.Classroom;
import bitcamp.java106.pms.util.Console;

public class ClassroomController implements Controller  {
    Scanner keyScan;
    ClassroomDao classroomDao;
    
    public ClassroomController(Scanner scanner, ClassroomDao classroomDao) {
        this.keyScan = scanner;
        this.classroomDao = classroomDao;
    }
    
    public void service(String menu, String option) {
        if (menu.equals("classroom/add")) {
            this.onClassroomAdd();
        } else if (menu.equals("classroom/list")) {
            this.onClassroomList();
        } else if (menu.equals("classroom/view")) {
            this.onClassroomView(option);
        } else if (menu.equals("classroom/update")) {
            this.onClassroomUpdate(option);
        } else if (menu.equals("classroom/delete")) {
            this.onClassroomDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    void onClassroomAdd() {
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
    
    void onClassroomList() {
        System.out.println("[수업 목록]");
        Classroom[] classrooms = classroomDao.list();
        for (Classroom classroom : classrooms) {
            System.out.printf("%d, %s, %s ~ %s, %s\n",
                    classroom.getNo(), classroom.getTitle(), 
                    classroom.getStartDate(), classroom.getEndDate(), classroom.getRoom());
        }
    }
    
    void onClassroomView(String option) {
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
    
    void onClassroomUpdate(String option) {
        System.out.println("[수업 변경]");
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        Classroom classroom = classroomDao.get(Integer.parseInt(option));
        if (classroom == null) {
            System.out.println("해당 게시물 번호가 없습니다.");
        } else {
            Classroom updateClassroom = new Classroom();
            System.out.printf("제목(%s)? ", classroom.getTitle());
            updateClassroom.setTitle(keyScan.nextLine());
            System.out.printf("시작일(%s)? ", classroom.getStartDate());
            updateClassroom.setStartDate(Date.valueOf(keyScan.nextLine()));
            System.out.printf("종료일(%s)? ", classroom.getEndDate());
            updateClassroom.setEndDate(Date.valueOf(keyScan.nextLine()));
            System.out.printf("교실번호(%s)? ", classroom.getRoom());
            updateClassroom.setRoom(keyScan.nextLine());
            
            updateClassroom.setNo(classroom.getNo());
            classroomDao.update(updateClassroom);
            System.out.println("변경하였습니다.");
        }
    }

    void onClassroomDelete(String option) {
        System.out.println("[수업 삭제]");
        if (option == null) {
            System.out.println("번호를 입력하시기 바랍니다.");
            return;
        }
        
        int i = Integer.parseInt(option);
        Classroom classroom = classroomDao.get(i);
        
        if (classroom == null) {
            System.out.println("해당 게시물이 없습니다.");
        } else {
            if (Console.confirm("정말 삭제하시겠습니까?")) {
                classroomDao.delete(i);
                System.out.println("삭제하였습니다.");
            }
        }
    }
    
}
