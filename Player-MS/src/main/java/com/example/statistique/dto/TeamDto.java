package com.example.statistique.dto;
//Statistique DTO from Statistique Microservice
public class StatistiqueDto implements java.io.Serializable {
    Long id ;
    String name ;

    public StatistiqueDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StatistiqueDto() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
