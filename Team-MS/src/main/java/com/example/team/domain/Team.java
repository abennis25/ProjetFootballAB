package com.example.team.domain;

public class Team {
 private Long id ;
 public String name ;

 public Team(Long id, String name) {
  this.id = id;
  this.name = name;
 }
 public Team() {

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
