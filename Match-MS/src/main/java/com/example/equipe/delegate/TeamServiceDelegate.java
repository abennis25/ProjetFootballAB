package com.example.equipe.delegate;


import com.example.equipe.dto.TeamDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class TeamServiceDelegate {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callTeamServiceAndGetData_Fallback", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")})
    public TeamDto callTeamServiceAndGetData(String name) {
        System.out.println("Getting Team details for " + name);
        ResponseEntity<TeamDto> response = this.restTemplate
                .exchange("http://localhost:8098/teambyname/" + name
                        , HttpMethod.GET
                        , null
                        ,TeamDto.class) ;

        System.out.println("Response Received as " + response + " -  " + new Date());
        return response.getBody();
    }

    @SuppressWarnings("unused")
    private TeamDto callTeamServiceAndGetData_Fallback(String name) {
        System.out.println("Service is down, fallback route enabled...");
        TeamDto fallbackResponse = new TeamDto();
        return fallbackResponse;
    }

}