package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Task;
import bitcamp.java106.pms.domain.Team;

@Component
public class TaskDao extends AbstractDao<Task> {
    
    public TaskDao() throws Exception {
        load();
    }
    
    public void load() throws Exception {
        Scanner in = new Scanner(new FileReader("data/task.data"));
        while (true) {
            try {
                String[] arr = in.nextLine().split(",");
                Task task = new Task(null);
                task.setNo(Integer.parseInt(arr[0]));
                task.setTitle(arr[1]);
                task.setStartDate(Date.valueOf(arr[2]));
                task.setEndDate(Date.valueOf(arr[3]));
                task.setState(Integer.parseInt(arr[4]));
                task.setTeam(new Team(arr[5]));
                task.setWorker(new Member(arr[6]));
                this.insert(task);
            } catch (Exception e) { // 데이터를 모두 읽었거나 파일 형식에 문제가 있다면,
                //e.printStackTrace();
                break; // 반복문을 나간다.
            }
        }
        in.close();
    }
    
    public void save() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("data/task.data"));
        
        Iterator<Task> tasks = this.list();
        
        while (tasks.hasNext()) {
            Task task = tasks.next();
            out.printf("%d,%s,%s,%s,%d,%s,%s\n", task.getNo(), task.getTitle(),
                    task.getStartDate(), task.getEndDate(),
                    task.getState(), task.getTeam().getName(), 
                    task.getWorker().getId());
        }
        out.close();
    }
    
    public Iterator<Task> list(String teamName) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : collection) {
            if (task.getTeam().getName().equalsIgnoreCase(teamName)) {
                tasks.add(task);
            }
        }
        return tasks.iterator();
    }

    @Override
    public int indexOf(Object key) {
        int taskNo = (Integer) key;
        for (int i = 0; i < collection.size(); i++) {
            Task task = collection.get(i);
            if (task.getNo() == taskNo) {
                return i;
            }
        }
        return -1;
    }
}