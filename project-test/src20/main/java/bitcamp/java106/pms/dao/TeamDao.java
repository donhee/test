package bitcamp.java106.pms.dao;

import java.util.LinkedList;

import bitcamp.java106.pms.domain.Team;

public class TeamDao {
    private LinkedList<Team> collection = new LinkedList<>();
    
    public void insert(Team team) {
        collection.add(team);
    }
    
    public Team[] list() {
        Team[] arr = new Team[collection.size()];
        for (int i = 0; i < collection.size(); i++) {
            arr[i] = collection.get(i);
        }
        return arr;
    }
    
    public Team get(String name) {
        int i;
        if ((i = this.getTeamIndex(name)) != -1) 
            return collection.get(i);
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
                    collection.get(i).getName().toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
    
}
