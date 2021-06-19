package com.server.EZY.repository.plan;

import com.server.EZY.model.plan.PlanDType;
import com.server.EZY.model.plan.PlanEntity;
import com.server.EZY.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
    List<PlanEntity> findAllByUserEntityAndPersonalPlanEntityNotNull(UserEntity userEntity);
}
