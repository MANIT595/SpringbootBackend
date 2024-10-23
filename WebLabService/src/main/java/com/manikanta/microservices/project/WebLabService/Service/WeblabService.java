package com.manikanta.microservices.project.WebLabService.Service;

import com.manikanta.microservices.project.WebLabService.Entity.Weblab;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeblabService {
    List<Weblab> getWeblabs();

    Weblab getWeblab(Long userId);

    void deleteWeblab(Long userId);

    void addWeblab(Weblab weblab);

    void updateWeblab(Weblab weblab);

}
