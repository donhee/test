package bitcamp.java106.pms.dao;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Classroom;

@Component
public class ClassroomDao extends AbstractDao<Classroom>{

    public ClassroomDao() throws Exception {
        load();
    }
    
    public void load() throws Exception {
        Scanner in = new Scanner(new FileReader("data/classroom.data"));
        while (true) {
            try {
                String[] arr = in.nextLine().split(",");
                Classroom classroom = new Classroom();
                classroom.setNo(Integer.parseInt(arr[0]));
                classroom.setTitle(arr[1]);
                classroom.setStartDate(Date.valueOf(arr[2]));
                classroom.setEndDate(Date.valueOf(arr[3]));
                classroom.setRoom(arr[4].equals(" ") ? "" : arr[4]);
                this.insert(classroom);
            } catch (Exception e) { // 데이터를 모두 읽었거나 파일 형식에 문제가 있다면,
                //e.printStackTrace();
                break; // 반복문을 나간다.
            }
        }
        in.close();
    }
    
    public void save() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("data/classroom.data"));
        
        Iterator<Classroom> classrooms = this.list();
        
        while (classrooms.hasNext()) {
            Classroom classroom = classrooms.next();
            out.printf("%d,%s,%s,%s,%s\n", classroom.getNo(), classroom.getTitle(),
                    classroom.getStartDate(), classroom.getEndDate(),
                    classroom.getRoom().equals("") ? " " : classroom.getRoom());
        }
        out.close();
    }
    
    @Override
    public int indexOf(Object key) {
        int classroomNo = (Integer) key;
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getNo() == classroomNo) {
                return i;
            }
        }
        return -1;
    }
    
    
    
}
