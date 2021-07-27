package com.server.EZY.model.plan.personal.service;

import com.server.EZY.model.member.MemberEntity;
import com.server.EZY.model.plan.personal.PersonalPlanEntity;

public interface PersonalPlanStrategy {
    PersonalPlanEntity singlePersonalPlanCheck(MemberEntity memberEntity, Long planIdx);
}
