package com.manikanta.microservices.project.WebLabService.Repository;

import com.manikanta.microservices.project.WebLabService.Entity.Weblab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeblabRepository extends JpaRepository<Weblab, Long> {
}
