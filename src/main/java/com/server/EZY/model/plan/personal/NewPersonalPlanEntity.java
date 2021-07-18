package com.server.EZY.model.plan.personal;

import com.server.EZY.model.plan.Period;
import com.server.EZY.model.plan.PlanInfo;
import com.server.EZY.model.plan.errand.ErrandStatus;
import com.server.EZY.model.plan.headOfPlan.enumType.PlanDType;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity @Table(name = "new_personal_plan")
@Builder @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class NewPersonalPlanEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_plan_id")
    private Long personalPlanIdx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "errand_status_id")
    private ErrandStatus errandStatus;

    @Embedded
    private PlanInfo planInfo;

    @Embedded
    private Period period;

    @Column(name = "repetition")
    private Boolean repetition;

    @Column(name = "d_type", nullable = true)
    @Enumerated(EnumType.STRING)
    private PlanDType dType;

    /**
     * personalPlan를 업데이트 하는 매서드
     * @param updatedPersonalPlanEntity 업데이트 할 PersonalPlan타입의 인자
     * @author 정시원
     */
    public void updatePersonalPlanEntity(NewPersonalPlanEntity updatedPersonalPlanEntity){
        repetition = updatedPersonalPlanEntity.repetition != null ? updatedPersonalPlanEntity.repetition : this.repetition;

        if(updatedPersonalPlanEntity.planInfo != null)
            this.planInfo.updatePlanInfo(updatedPersonalPlanEntity.planInfo);
        if(updatedPersonalPlanEntity.period != null)
            this.period.updatePeriod(updatedPersonalPlanEntity.period);
    }
}
