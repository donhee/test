package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.Team;
import bitcamp.java106.pms.util.ArrayList;

public class TeamDao {
    private ArrayList collection = new ArrayList();
    
    public void insert(Team team) {
        collection.add(team);
    }
    
    public Team[] list() {
        Team[] arr = new Team[collection.size()];
        for (int i = 0; i < collection.size(); i++) {
            arr[i] = (Team) collection.get(i);
        }
        return arr;
    }
    
    public Team get(String name) {
        int i;
        if ((i = this.getTeamIndex(name)) != -1) 
            return (Team) collection.get(i);
        return null;
    }
    
    public void update(Team team) {
        int i = this.getTeamIndex(team.getName());
        if (i != -1)
            collection.set(i, team);
    }

    public void delete(String name) {
        int i = this.getTeamIndex(name);
        if (i != -1) 
            collection.remove(i);
    }

    private int getTeamIndex(String name) {
        for (int i = 0; i < collection.size(); i++) {
            if (name.toLowerCase().equals(
                    ((Team)collection.get(i)).getName().toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
    
}
