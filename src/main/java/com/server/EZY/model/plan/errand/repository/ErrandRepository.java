package com.server.EZY.model.plan.errand.repository;

import com.server.EZY.model.plan.errand.ErrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrandRepository extends JpaRepository<ErrandEntity, Long> {
}