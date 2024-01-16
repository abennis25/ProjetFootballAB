package com.example.statistique.delegate;

import java.util.Date;

import com.example.statistique.dto.StatistiqueDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class StatistiqueServiceDelegate {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callStatistiqueServiceAndGetData_Fallback", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMillisecond", value = "1000")})
    public StatistiqueDto callStatistiqueServiceAndGetData(Long id) {
        System.out.println("Getting Statistique details for " + id);
        ResponseEntity<StatistiqueDto> response = this.restTemplate
                .exchange("http://localhost:8098/teams/" + id
                        , HttpMethod.GET
                        , null
                        ,StatistiqueDto.class) ;

        System.out.println("Response Received as " + response + " -  " + new Date());
        return response.getBody();
    }

    @SuppressWarnings("unused")
    private String callStatistiqueServiceAndGetData_Fallback(Long id) {
        System.out.println(" Service is down!!! fallback route enabled...");
        return "CIRCUIT BREAKER ENABLED!!!No Response From Statistique Service at this moment. Service will be back shortly - " + new Date();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}