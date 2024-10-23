package com.manikanta.microservices.project.WebLabService.Service.Implementaion;

import com.manikanta.microservices.project.WebLabService.Entity.Weblab;
import com.manikanta.microservices.project.WebLabService.Repository.WeblabRepository;
import com.manikanta.microservices.project.WebLabService.Service.WeblabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeblabServiceImplementation implements WeblabService {

    @Autowired
    private WeblabRepository weblabRepository;

    @Override
    public List<Weblab> getWeblabs() {
        return weblabRepository.findAll();
    }

    @Override
    public Weblab getWeblab(Long weblabId) {
        System.out.println("IN getweblab Service method");
        return weblabRepository.findById(weblabId).get();
    }

    @Override
    public void deleteWeblab(Long weblabId) {
        weblabRepository.deleteById(weblabId);
    }

    @Override
    public void addWeblab(Weblab weblab) {
        weblabRepository.save(weblab);
    }

    @Override
    public void updateWeblab(Weblab weblab) {
        weblabRepository.save(weblab);
    }
}
