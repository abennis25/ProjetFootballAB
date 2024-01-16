package com.example.statistique.controller;

import com.example.statistique.domain.Statistique;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "StatistiqueService", description = "REST Apis  to Statistique Entity")
public class StatistiqueServiceController {

    private static Map<String , List<Statistique>> mapStatistique =  new HashMap<String,List<Statistique>>();


    // TODO create some Statistiques in a static DB
    static {
        mapStatistique = new HashMap<>();
        List<Statistique> Statistiques = new ArrayList<>();

}


}
