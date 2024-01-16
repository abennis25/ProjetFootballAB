package com.example.equipe.domain;

import com.example.equipe.dto.TeamDto;

public class Match {
 private Long id ;
 public TeamDto team1 ;
 public TeamDto team2 ;
 public int score1 ;
 public int score2 ;

 public Match() {
 }

 public Match(Long id, TeamDto team1, TeamDto team2, int score1, int score2) {
  this.id = id;
  this.team1 = team1;
  this.team2 = team2;
  this.score1 = score1;
  this.score2 = score2;
 }

 public Long getId() {
  return id;
 }

 public TeamDto getTeam1() {
  return team1;
 }

 public TeamDto getTeam2() {
  return team2;
 }

 public int getScore1() {
  return score1;
 }

 public int getScore2() {
  return score2;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public void setTeam1(TeamDto team1) {
  this.team1 = team1;
 }

 public void setTeam2(TeamDto team2) {
  this.team2 = team2;
 }

 public void setScore1(int score1) {
  this.score1 = score1;
 }

 public void setScore2(int score2) {
  this.score2 = score2;
 }
}
