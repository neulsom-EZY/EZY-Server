package com.server.EZY.model.plan.errand.dto;

import com.server.EZY.model.member.MemberEntity;
import com.server.EZY.model.plan.embeddedTypes.Period;
import com.server.EZY.model.plan.embeddedTypes.PlanInfo;
import com.server.EZY.model.plan.errand.ErrandEntity;
import com.server.EZY.model.plan.errand.ErrandStatusEntity;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class ErrandSetDto {
    // title, explanation
    @NotNull
    private PlanInfo planInfo;
    // startTime, endTime
    @NotNull
    private Period period;
    private String location;
    @NotNull
    private String recipient;

    public ErrandEntity saveToEntity(MemberEntity memberEntity, ErrandStatusEntity errandStatusEntity){
        return ErrandEntity.builder()
                .memberEntity(memberEntity)
                .tagEntity(null)
                .planInfo(planInfo)
                .period(period)
                .errandStatusEntity(errandStatusEntity)
                .location(location)
                .build();
    }
}