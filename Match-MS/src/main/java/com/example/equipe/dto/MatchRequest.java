package com.example.equipe.dto;

import java.io.Serializable;

public class MatchRequest implements Serializable {
    public String team1 ;
    public String team2 ;
    public int score1 ;
    public int score2 ;

    public MatchRequest() {
    }

    public MatchRequest(String team1, String team2, int score1, int score2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
