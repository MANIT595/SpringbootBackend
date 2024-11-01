package com.manikanta.microservices.project.UserService.Service;

import com.manikanta.microservices.project.UserService.DTO.OrderDTO;
import com.manikanta.microservices.project.UserService.DTO.WeblabDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "weblab-service")
public interface FeignWeblabClient {
    @GetMapping("api/weblabs/{weblab-id}")
    WeblabDTO getWeblabById(@PathVariable("weblab-id") Long weblabId);
}
