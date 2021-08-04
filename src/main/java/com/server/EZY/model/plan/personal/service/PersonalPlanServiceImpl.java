package com.server.EZY.model.plan.personal.service;

import com.server.EZY.model.member.MemberEntity;
import com.server.EZY.model.plan.personal.PersonalPlanEntity;
import com.server.EZY.model.plan.personal.dto.PersonalPlanSetDto;
import com.server.EZY.model.plan.personal.repository.PersonalPlanRepository;
import com.server.EZY.model.plan.personal.service.strategy.PersonalPlanStrategy;
import com.server.EZY.model.plan.tag.TagEntity;
import com.server.EZY.model.plan.tag.repository.TagRepository;
import com.server.EZY.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalPlanServiceImpl implements PersonalPlanService{

    private final CurrentUserUtil userUtil;
    private final TagRepository tagRepository;
    private final PersonalPlanRepository personalPlanRepository;
    private final PersonalPlanStrategy personalPlanStrategy;

    /**
     * personalPlan을 "생성"하기 위해 사용되는 비즈니스 로직입니다.
     * @param personalPlan
     * @return PersonalPlanEntity
     * @author 전지환
     */
    @Transactional
    @Override
    public PersonalPlanEntity createPersonalPlan(PersonalPlanSetDto personalPlan) {
        MemberEntity currentUser = userUtil.getCurrentUser();
        TagEntity tagEntity = tagRepository.findByTagIdx(personalPlan.getTagIdx());
        // 저장요청
        return personalPlanRepository.save(personalPlan.saveToEntity(currentUser, tagEntity));
    }

    /**
     * 내 전체 personalPlan을 "조회"하기 위해 사용되는 비즈니스 로직입니다.
     * @return List<PersonalPlanEntity>
     * @author 전지환
     */
    @Override
    public List<PersonalPlanEntity> getAllPersonalPlan() {
        MemberEntity currentUser = userUtil.getCurrentUser();
        return personalPlanRepository.findAllPersonalPlanByMemberEntity(currentUser);
    }

    @Override
    public List<PersonalPlanEntity> getThisStartDateTimePersonalEntities(LocalDate startDate) {
        MemberEntity currentUser = userUtil.getCurrentUser();
        return personalPlanRepository.findAllPersonalPlanEntitiesByMemberEntityAndPeriodStartDateTime(currentUser, startDate);
    }

    /**
     * 하나의 personalPlan을 "단건 조회"하기 위해 사용되는 비즈니스 로직입니다.
     * @param planIdx
     * @return PersonalPlanEntity
     */
    @Override
    public PersonalPlanEntity getThisPersonalPlan(Long planIdx) {
        MemberEntity currentUser = userUtil.getCurrentUser();
        return personalPlanStrategy.singlePersonalPlanCheck(currentUser, planIdx);
    }

    /**
     * 내가 지정한 personalPlan을 업데이트 하는 비즈니스 로직입니다.
     * @param planIdx
     * @param personalPlan
     * @return PersonalPlanEntity
     * @author 전지환
     */
    @Transactional
    @Override
    public PersonalPlanEntity updateThisPersonalPlan(Long planIdx, PersonalPlanSetDto personalPlan) {
        MemberEntity currentUser = userUtil.getCurrentUser();
        TagEntity tagEntity = tagRepository.findByTagIdx(personalPlan.getTagIdx());

        PersonalPlanEntity targetPersonalPlan = personalPlanStrategy.singlePersonalPlanCheck(currentUser, planIdx);
        targetPersonalPlan.updatePersonalPlanEntity(
                personalPlan.saveToEntity(currentUser, tagEntity)
        );

        return targetPersonalPlan;
    }

    /**
     * 내가 지정한 personalPlan을 삭제하는 비즈니스 로직입니다.
     * @param planIdx
     * @author 전지환
     */
    @Transactional
    @Override
    public void deleteThisPersonalPlan(Long planIdx) {
        MemberEntity currentUser = userUtil.getCurrentUser();
        personalPlanRepository.delete(personalPlanStrategy.singlePersonalPlanCheck(currentUser, planIdx));
    }
}
