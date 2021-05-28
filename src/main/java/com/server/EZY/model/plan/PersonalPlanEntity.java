package com.server.EZY.model.plan;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PersonalPlan")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class PersonalPlanEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersonalPlanId")
    private Long personalPlanIdx;

    @Column(name = "PlanName", nullable = false)
    @Size(min = 1, max = 30)
    private String planName;

    @Column(name = "PlanWhen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date when;

    @Column(name = "PlanWhere")
    private String where;

    @Column(name = "PlanWhat")
    private String what;

    @Column(name = "PlanHow")
    private String how;

    @Column(name = "PlanWho")
    private String who;

    @Column(name = "PlanRepeat")
    private boolean repeat;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "Category",
            joinColumns = @JoinColumn(name = "PersonalPlanId")
    )
    @Builder.Default
    private List<String> categories = new ArrayList<>();
}