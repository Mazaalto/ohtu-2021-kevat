package ohtu;

//{"name":"Travis Zajac","nationality":"CAN","assists":16,"goals":9,"penalties":28,"team":"NJD","games":69}
public class Player implements Comparable<Player> {

    private String name;
    private String nationality;
    private String team;
    private Integer assists;
    private Integer goals;
    private Integer penalties;
    private Integer games;

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;

    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public Integer getAssists() {
        return assists;
    }

    public Integer getGoals() {
        return goals;
    }

    public Integer goalsAndAssists() {
        return goals + this.assists;
    }

    //Henrik Borgstrom team FLA goals 0 assists 0
    @Override
    public String toString() {
        return name + " from team : " + team + " " + this.goals + " goals "+ assists +" assists = " + this.goalsAndAssists();
    }

    @Override
    public int compareTo(Player t) {
        //pienimmästä isompaan
        //return this.goalsAndAssists() - t.goalsAndAssists();
        //isommasta pienempään?
        return t.goalsAndAssists() - this.goalsAndAssists();
        
    }

}
