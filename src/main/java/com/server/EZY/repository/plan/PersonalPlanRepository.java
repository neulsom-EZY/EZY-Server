package com.server.EZY.repository.plan;

import com.server.EZY.model.plan.personal.PersonalPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalPlanRepository extends JpaRepository<PersonalPlanEntity, Long> {
}
