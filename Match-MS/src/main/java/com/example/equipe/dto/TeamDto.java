package com.example.equipe.dto;

public class TeamDto implements java.io.Serializable {
    Long id ;
    String name ;

    public TeamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeamDto() {

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
