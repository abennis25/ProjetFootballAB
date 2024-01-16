package com.example.statistique.domain;

import com.example.statistique.dto.StatistiqueDto;

public class Player {
 private Long id ;
 public String name ;

public StatistiqueDto team ;

    public Player(Long id, String name, StatistiqueDto team) {
    this.id = id;
    this.name = name;
    this.team = team;

    }
    public Player() {

    }
    public Long getId() {
    return id;
    }

 public void setId(Long id) {
  this.id = id;
 }

 public void setName(String name) {
  this.name = name;
 }



    public String getName() {
        return name;
    }

    public StatistiqueDto getStatistique() {
        return team;
    }

    public void setStatistique(StatistiqueDto team) {
        this.team = team;
    }
}
