package com.server.EZY.model.plan;

import com.server.EZY.model.baseTime.BaseTimeEntity;
import com.server.EZY.model.member.MemberEntity;
import com.server.EZY.model.plan.embeddedTypes.Period;
import com.server.EZY.model.plan.embeddedTypes.PlanInfo;
import com.server.EZY.model.plan.tag.TagEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity @Table(name = "plan")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "d_type") //d_type 자동 생성
@Builder @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class PlanEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    protected Long planIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    protected MemberEntity memberEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = true)
    protected TagEntity tagEntity;

    @Embedded
    protected PlanInfo planInfo;

    @Embedded
    protected Period period;

    /**
     * 일정들의 기본적인 정보들을 생성하는 PlanEntity객체의 생성자
     * @param memberEntity 유저와 연관관계를 맻을 MemberEntity타입의 객체 (null 비허용)
     * @param tagEntity 태그를 지정하는 TagEntity타입의 객체 (null 허용)
     * @param planInfo 일정의 기본적인 정보(title, explanation)을 가지고 있는 PlanInfo타입의 객체 (null 비허용)
     * @param period 일정의 시작/종료(startTime, endTime) 시간을 가지고 있는 Period타입의 객체 (null 비허용)
     * @author 정시원
     */
    protected PlanEntity(MemberEntity memberEntity, TagEntity tagEntity, PlanInfo planInfo, Period period){
        if(memberEntity != null && planInfo != null && period != null){
            this.memberEntity = memberEntity;
            this.tagEntity = tagEntity;
            this.planInfo = planInfo;
            this.period = period;
        }else{
            log.error("PlanEntity의 생성자에 null값이 인수로 주입되었습니다.\n" +
                    "MemberEntity = {} \n" +
                    "TagEntity = {}\n" +
                    "PlanInfo = {}\n" +
                    "Period = {}\n",
                    memberEntity, tagEntity, planInfo, period
            );
            throw new NullPointerException("PlanEntity의 생성자에 null값이 인수로 주입되었습니다.");
        }
    }
}