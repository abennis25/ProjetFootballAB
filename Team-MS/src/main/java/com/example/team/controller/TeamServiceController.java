package com.example.team.controller;

import com.example.team.domain.Team;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "TeamService", description = "REST Apis  to Team Entity")
public class TeamServiceController {

    private static Map<String , List<Team>> mapTeam =  new HashMap<String,List<Team>>();


    // TODO create some Teams in a static DB
    static {
        mapTeam = new HashMap<>();
        List<Team> Teams = new ArrayList<>();

        Team e1 = new Team(1L, "Barcelone");
        Team e2 = new Team(2L, "Real Madrid");
        Team e3 = new Team(3L, "Manchester United");
        Team e4 = new Team(4L, "Liverpool");
        Team e5 = new Team(5L, "Chelsea");
        Team e6 = new Team(6L, "Arsenal");
        Team e7 = new Team(7L, "Manchester City");
        Team e8 = new Team(8L, "Tottenham");


        Teams.add(e1);
        Teams.add(e2);
        Teams.add(e3);
        Teams.add(e4);
        Teams.add(e5);
        Teams.add(e6);
        Teams.add(e7);
        Teams.add(e8);

        mapTeam.put("List", Teams);
    }
    @ApiOperation(value = "GetTeamById ", response = Iterable.class, tags = "GetTeamById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!"),
            @ApiResponse(code = 404, message = "not found!") })
    @GetMapping("/teams/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return mapTeam.get("List").stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @ApiOperation(value = "GetTeamByName ", response = Iterable.class, tags = "GetTeamByName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!"),
            @ApiResponse(code = 404, message = "not found!") })
    @GetMapping("/teambyname/{name}")
    public Team getTeamByName(@PathVariable String name) {
        return mapTeam.get("List").stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @ApiOperation(value = "CreateTeam ", response = Iterable.class, tags = "CreateTeam")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!"),
            @ApiResponse(code = 404, message = "not found!") })
    @PostMapping("/teams")
    public void addTeam(@RequestBody Team Team) {
        mapTeam.get("List").add(Team);
    }


    @ApiOperation(value = "UpdateTeam ", response = Iterable.class, tags = "UpdateTeam")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!"),
            @ApiResponse(code = 404, message = "not found!") })
    @PutMapping("/teams/{id}")
    public void updateTeam(@PathVariable Long id, @RequestBody Team Team) {
        mapTeam.get("List").stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .ifPresent(e -> {
                    e.setName(Team.getName());
                });
    }
    @ApiOperation(value = "DeleteTeam ", response = Iterable.class, tags = "DeleteTeam")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!"),
            @ApiResponse(code = 404, message = "not found!") })
    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable Long id) {
        mapTeam.get("List").removeIf(e -> e.getId().equals(id));
    }
}
