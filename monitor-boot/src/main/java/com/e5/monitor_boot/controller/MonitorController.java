package com.e5.monitor_boot.controller;

import com.e5.monitor_boot.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @PostMapping(path = {"/performHealthCheck"}, produces = "application/json")
    public ResponseEntity<String> checkHealth(@RequestBody() String deploymentId) {
        boolean isHealthy = monitorService.performHealthCheck(deploymentId);
        return new ResponseEntity<>(isHealthy?"Healthy":"Unhealthy", HttpStatus.OK);
    }

    @PostMapping(path = {"/restartDeployment"}, produces = "application/json")
    public ResponseEntity<String> restartDeployment(@RequestBody() String deploymentId) {
        String result = monitorService.restartDeployment(deploymentId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
