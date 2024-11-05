package com.manikanta.microservices.project.WebLabService.Service.Implementaion;

import com.manikanta.microservices.project.WebLabService.Entity.Weblab;
import com.manikanta.microservices.project.WebLabService.Repository.WeblabRepository;
import com.manikanta.microservices.project.WebLabService.Service.WeblabService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeblabServiceImplementation implements WeblabService {

    @Autowired
    private WeblabRepository weblabRepository;

    private static final Logger logger = LoggerFactory.getLogger(WeblabServiceImplementation.class);

    @Override
    public List<Weblab> getWeblabs() {
        logger.info("inside getWeblabs method");
        return weblabRepository.findAll();
    }

    @Override
    public Weblab getWeblab(Long weblabId) {
        logger.info("inside getweblab method");
        return weblabRepository.findById(weblabId).get();
    }

    @Override
    public void deleteWeblab(Long weblabId) {
        logger.info("inside deleteWeblab method");
        weblabRepository.deleteById(weblabId);
    }

    @Override
    public void addWeblab(Weblab weblab) {
        logger.info("inside addWeblab method");
        weblabRepository.save(weblab);
    }

    @Override
    public void updateWeblab(Weblab weblab) {
        logger.info("inside updateWeblab method");
        weblabRepository.save(weblab);
    }
}
