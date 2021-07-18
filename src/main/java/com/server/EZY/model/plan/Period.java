package com.server.EZY.model.plan;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 일정(Plan)의 기간을 임베디드 타입으로 분리한 클래스
 * @author 정시원
 */
@Embeddable
@Builder @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class Period {

    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    /**
     * Period객체를 업데이트 한다.
     * @param updatedPeriod 업데이트 할 Period타입의 인자
     * @author 정시원
     */
    public void updatePeriod(Period updatedPeriod){
        this.startTime = updatedPeriod.startTime != null ? updatedPeriod.startTime : this.startTime;
        this.endTime = updatedPeriod.endTime != null ? updatedPeriod.endTime : this.endTime;
    }
}
