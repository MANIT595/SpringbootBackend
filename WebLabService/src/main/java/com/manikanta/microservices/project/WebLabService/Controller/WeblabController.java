package com.manikanta.microservices.project.WebLabService.Controller;


import com.manikanta.microservices.project.WebLabService.Entity.Weblab;
import com.manikanta.microservices.project.WebLabService.Repository.WeblabRepository;
import com.manikanta.microservices.project.WebLabService.Service.WeblabService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/weblabs")
@AllArgsConstructor
public class WeblabController {

    @Autowired
    private WeblabService weblabService;

    @GetMapping()
    public List<Weblab> getProducts() {
        return weblabService.getWeblabs();

    }

    @GetMapping("{weblab-id}")
    public Weblab getProductById(@PathVariable("weblab-id") Long id) {
        return weblabService.getWeblab(id);
    }


    @DeleteMapping("{weblab-id}")
    public void deleteWeblabById(@PathVariable("weblab-id") Long id) {
        weblabService.deleteWeblab(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Weblab weblab){
        weblabService.addWeblab(weblab);
    }

    @PatchMapping
    public void updateWeblab(@RequestBody Weblab weblab){
        weblabService.updateWeblab(weblab);
    }
}
