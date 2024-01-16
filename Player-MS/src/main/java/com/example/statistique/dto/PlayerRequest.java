package com.example.statistique.dto;

import java.io.Serializable;

public class PlayerRequest implements Serializable {
    String name ;
    Long teamId ;

    public PlayerRequest() {
    }

    public PlayerRequest(String name, Long teamId) {
        this.name = name;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public Long getStatistiqueId() {
        return teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatistiqueId(Long teamId) {
        this.teamId = teamId;
    }
}
