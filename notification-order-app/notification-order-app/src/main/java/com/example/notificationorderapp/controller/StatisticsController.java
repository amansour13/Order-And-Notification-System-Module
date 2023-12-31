package com.example.notificationorderapp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationorderapp.service.StatisticsService;
import com.example.notificationorderapp.service.StatisticsServiceImpl;


import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    
    private StatisticsService statisticsService = new StatisticsServiceImpl();

    @GetMapping("/phone")
    public ResponseEntity<String> getMostNotifyPhone() {
        
        String status = statisticsService.getMostNotifyPhone();
        if (status == null) {
            return new ResponseEntity<>("No statistics yet", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/email")
    ResponseEntity<String> getMostNotifyEmail(){
       
        
        String status = statisticsService.getMostNotifyEmail();
        if (status == null) {
            return new ResponseEntity<>("No statistics yet", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

     @GetMapping("/template")
    ResponseEntity<String> getMostNotifyTemp(){
        
        String status = statisticsService.getMostNotifyTemp();
        if (status == null) {
            return new ResponseEntity<>("No statistics yet", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
    
}




