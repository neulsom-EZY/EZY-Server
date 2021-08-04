package com.server.EZY.model.plan.personal.controller;

import com.server.EZY.model.plan.personal.dto.PersonalPlanSetDto;
import com.server.EZY.model.plan.personal.service.PersonalPlanService;
import com.server.EZY.response.ResponseService;
import com.server.EZY.response.result.CommonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/v1/plan/personal")
@RequiredArgsConstructor
public class PersonalPlanController {
    private final PersonalPlanService personalPlanService;
    private final ResponseService responseService;

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "RefreshToken", value = "로그인 성공 후 refresh_token", required = false, dataType = "String", paramType = "header")
    })
    public CommonResult savePersonalPlan(@RequestBody PersonalPlanSetDto personalPlanSetDto) {
        personalPlanService.createPersonalPlan(personalPlanSetDto);
        return responseService.getSuccessResult();
    }

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "RefreshToken", value = "로그인 성공 후 refresh_token", required = false, dataType = "String", paramType = "header")
    })
    public CommonResult getAllPersonalPlan(
            @RequestParam(value = "startDateTime", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTimeOrNull,
            @RequestParam(value = "endDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTimeOrNull
    ){
        if (startDateTimeOrNull != null){
            return responseService.getListResult(personalPlanService.getThisStartDateTimePersonalEntities(startDateTimeOrNull));
        }
//        else if(endDateTimeOrNull != null){
//            return responseService.getListResult(personalPlanService.getAllPersonalPlan());
//        } else if(startDateTimeOrNull!=null && endDateTimeOrNull!=null){
//            return responseService.getListResult(personalPlanService.getAllPersonalPlan());
//        }
        else{
            return responseService.getListResult(personalPlanService.getAllPersonalPlan());
        }
    }

    @GetMapping("/{planIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "RefreshToken", value = "로그인 성공 후 refresh_token", required = false, dataType = "String", paramType = "header")
    })
    public CommonResult getThisPersonalPlan(@PathVariable("planIdx") Long planIdx){
        return responseService.getSingleResult(personalPlanService.getThisPersonalPlan(planIdx));
    }

    @PutMapping("/{planIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "RefreshToken", value = "로그인 성공 후 refresh_token", required = false, dataType = "String", paramType = "header")
    })
    public CommonResult updateThisPersonalPlan(@PathVariable Long planIdx, @RequestBody PersonalPlanSetDto personalPlanSetDto) throws Exception {
        personalPlanService.updateThisPersonalPlan(planIdx, personalPlanSetDto);
        return responseService.getSuccessResult();
    }


    @DeleteMapping("/{planIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "RefreshToken", value = "로그인 성공 후 refresh_token", required = false, dataType = "String", paramType = "header")
    })
    public CommonResult deleteThisPersonalPlan(@PathVariable Long planIdx){
        personalPlanService.deleteThisPersonalPlan(planIdx);
        return responseService.getSuccessResult();
    }
}
