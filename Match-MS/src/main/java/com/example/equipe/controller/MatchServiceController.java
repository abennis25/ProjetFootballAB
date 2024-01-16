package com.example.equipe.controller;

import com.example.equipe.delegate.TeamServiceDelegate;
import com.example.equipe.domain.Match;
import com.example.equipe.dto.MatchRequest;
import com.example.equipe.dto.TeamDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
@Api(value = "MatchService", description = "REST Apis  to Match Entity")
@RestController
public class MatchServiceController {
    @Autowired
    TeamServiceDelegate teamServiceDelegate;
    RestTemplate restTemplate = new RestTemplate();
    private Map<Long, Match> matches = new ConcurrentHashMap<>();
    //Create Match
    @ApiOperation(value = "CreateMatch ", response = Iterable.class, tags = "createMatch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!"),
            @ApiResponse(code = 404, message = "not found!") })
    @PostMapping("/match")
    public ResponseEntity<Match> createMatch(@RequestBody MatchRequest matchRequest) {
// Vérifie si les équipes existent
        if (!isTeamExist(matchRequest.getTeam1())) {
            return ResponseEntity.badRequest().body(null);
        }
        if (!isTeamExist(matchRequest.getTeam2())) {
            return ResponseEntity.badRequest().body(null);
        }
        //Recupere l'equipe du joueur
        TeamDto team1  = new TeamDto() ;
         team1 =   teamServiceDelegate.callTeamServiceAndGetData(matchRequest.getTeam1());
        TeamDto team2  = new TeamDto() ;
        team2 = teamServiceDelegate.callTeamServiceAndGetData(matchRequest.getTeam2());

        Random random = new Random();
        if(team1 == null || team2 == null){
            return ResponseEntity.badRequest().body(null);
        }
        long randomId = Math.abs(random.nextLong()) % 1001;
        Match match = new Match(randomId,team1,team2,2,3);
        // Ajoute le joueur à la liste
        matches.put(match.getId(), match);
        return ResponseEntity.ok(match);
    }

    //Get match by Id
    @ApiOperation(value = "GetMatchById ", response = Iterable.class, tags = "GetMatchById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })


    private boolean isTeamExist(String name) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8098/teambyname/" + name, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
