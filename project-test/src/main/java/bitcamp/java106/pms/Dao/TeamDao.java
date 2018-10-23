package bitcamp.java106.pms.Dao;

import bitcamp.java106.pms.domain.Member;
import bitcamp.java106.pms.domain.Team;

public class TeamDao {
    Team[] teams = new Team[100];
    int teamIndex = 0;
    
    public void insert(Team team) {
        this.teams[this.teamIndex++] = team;
    }
    
    public Team[] list() {
        Team[] arr = new Team[this.teamIndex];
        for (int i = 0; i < this.teamIndex; i++) {
            arr[i] = this.teams[i];
        }
        return arr;
    }
    
    public Team get(String teamName) {
        int i = this.getTeamIndex(teamName);
        if (i == -1)
            return null;
        return this.teams[i];
    }
    
    public void update(Team team) {
        int i = getTeamIndex(team.teamName);
        if (i != -1)
            this.teams[i] = team;
    }
    
    public void delete(String teamName) {
        int i = getTeamIndex(teamName);
        if (i != -1)
            this.teams[i] = null;
    }
    
    private int getTeamIndex(String teamName) {
        for (int i = 0; i < teamIndex; i++) {
            if (teams[i] == null) continue;
            if (teamName.equals(teams[i].teamName.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}
