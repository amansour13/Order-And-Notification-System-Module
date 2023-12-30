package com.example.notificationorderapp.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationorderapp.service.StatisticsService;
import com.example.notificationorderapp.service.StatisticsServiceImpl;


import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/")
public class StatisticsController {
    

    @GetMapping("/phoneStatistics")
    public ResponseEntity<String> getMostNotifyPhone() {
        StatisticsService statisticsService = new StatisticsServiceImpl();
        String status = statisticsService.getMostNotifyPhone();
        if (status == null) {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/emailStatistics")
    ResponseEntity<String> getMostNotifyEmail(){
       
        StatisticsService statisticsService = new StatisticsServiceImpl();
        String status = statisticsService.getMostNotifyEmail();
        if (status == null) {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

     @GetMapping("/templateStatistics")
    ResponseEntity<String> getMostNotifyTemp(){
        StatisticsService statisticsService = new StatisticsServiceImpl();
        String status = statisticsService.getMostNotifyTemp();
        if (status == null) {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
    
}




