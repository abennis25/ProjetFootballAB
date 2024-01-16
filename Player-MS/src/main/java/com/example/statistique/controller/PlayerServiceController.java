package com.example.statistique.controller;

import com.example.statistique.delegate.StatistiqueServiceDelegate;
import com.example.statistique.domain.Player;
import com.example.statistique.dto.PlayerRequest;
import com.example.statistique.dto.StatistiqueDto;
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
@Api(value = "PlayerService", description = "REST Apis  to Player Entity")
@RestController
public class PlayerServiceController {
    @Autowired
    StatistiqueServiceDelegate teamServiceDelegate;
    RestTemplate restTemplate = new RestTemplate();
    private Map<Long, Player> players = new ConcurrentHashMap<>();
    //Create Player
    @ApiOperation(value = "CreatePlayer ", response = Iterable.class, tags = "createPlayer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!"),
            @ApiResponse(code = 404, message = "not found!") })
    @PostMapping("/player")
    public ResponseEntity<Player> createPlayer(@RequestBody PlayerRequest player) {
        // Vérifie si l'équipe existe
        if (!isStatistiqueExist(player.getStatistiqueId())) {
            return ResponseEntity.badRequest().body(null); // Ou toute autre gestion d'erreur appropriée
        }
        //Recupere l'equipe du joueur
        StatistiqueDto team = teamServiceDelegate.callStatistiqueServiceAndGetData(player.getStatistiqueId());
        Random random = new Random();
        Player playerHelp = new Player(random.nextLong(), player.getName(), team);


        // Ajoute le joueur à la liste
        players.put(playerHelp.getId(), playerHelp);
        return ResponseEntity.ok(playerHelp);
    }

    //Get player by Id
    @ApiOperation(value = "GetPlayerById ", response = Iterable.class, tags = "GetPlayerById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    //Get Player By Id with his team
    @GetMapping("/player/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = players.get(id);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        // Récupère l'équipe du joueur
        StatistiqueDto team = teamServiceDelegate.callStatistiqueServiceAndGetData(player.getStatistique().getId());
        player.setStatistique(team);
        return ResponseEntity.ok(player);
    }

    //Delete Player By Id
    @ApiOperation(value = "DeletePlayerById ", response = Iterable.class, tags = "DeletePlayerById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @DeleteMapping("/player/{id}")
    public ResponseEntity<Player> deletePlayerById(@PathVariable Long id) {
        Player player = players.get(id);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        players.remove(id);
        return ResponseEntity.ok(player);
    }

    //Update Player
    @ApiOperation(value = "UpdatePlayer ", response = Iterable.class, tags = "UpdatePlayer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping("/player/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequest player) {
        Player playerHelp = players.get(id);
        if (playerHelp == null) {
            return ResponseEntity.notFound().build();
        }
        // Vérifie si l'équipe existe
        if (!isStatistiqueExist(player.getStatistiqueId())) {
            return ResponseEntity.badRequest().body(null);
        }
        StatistiqueDto team = teamServiceDelegate.callStatistiqueServiceAndGetData(player.getStatistiqueId());
        playerHelp.setName(player.getName());
        playerHelp.setStatistique(team);
        return ResponseEntity.ok(playerHelp);
    }


    private boolean isStatistiqueExist(Long teamId) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8098/teams/" + teamId, String.class);
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
