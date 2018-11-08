package bitcamp.java106.pms.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.Scanner;

import bitcamp.java106.pms.annotation.Component;
import bitcamp.java106.pms.domain.Team;

@Component
public class TeamDao extends AbstractDao<Team> {
    
    public TeamDao() throws Exception {
        load();
    }
    
    public void load() throws Exception {
        Scanner in = new Scanner(new FileReader("data/team.data"));
        while (true) {
            try {
                String[] arr = in.nextLine().split(",");
                Team team = new Team();
                team.setName(arr[0]);
                team.setDescription(arr[1]);
                team.setMaxQty(Integer.parseInt(arr[2]));
                team.setStartDate(Date.valueOf(arr[3]));
                team.setEndDate(Date.valueOf(arr[4]));
                this.insert(team);
            } catch (Exception e) { // 데이터를 모두 읽었거나 파일 형식에 문제가 있다면,
                //e.printStackTrace();
                break; // 반복문을 나간다.
            }
        }
        in.close();
    }
    
    public void save() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("data/team.data"));
        
        Iterator<Team> teams = this.list();
        
        while (teams.hasNext()) {
            Team team = teams.next();
            out.printf("%s,%s,%d,%s,%s\n", 
                    team.getName(), team.getDescription(), team.getMaxQty(),
                    team.getStartDate(), team.getEndDate());
        }
        out.close();
    }
        
    public int indexOf(Object key) {
        String name = (String) key;
        for (int i = 0; i < collection.size(); i++) {
            if (name.equalsIgnoreCase(collection.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }
}