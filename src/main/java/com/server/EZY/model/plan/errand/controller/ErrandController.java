package com.server.EZY.model.plan.errand.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.server.EZY.model.plan.errand.dto.ErrandSetDto;
import com.server.EZY.model.plan.errand.service.ErrandService;
import com.server.EZY.response.ResponseService;
import com.server.EZY.response.result.CommonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/errand")
@RequiredArgsConstructor
public class ErrandController {

    private final ErrandService errandService;
    private final ResponseService responseService;

    /**
     * 심부름 알람을 보내는 Controller (사용자 입장에선 알람을 받게된다)
     * @param username
     * @return
     * @author 배태현
     */
    @GetMapping("/{username}")
    public CommonResult getErrandAlarm(@PathVariable("username") String username) {
        return responseService.getSuccessResult();
    }

    /**
     * 해당 심부름을 상세조회하는 Controller
     * @param errandIdx
     * @return
     * @author 배태현
     */
    @GetMapping("/errandIdx")
    public CommonResult thisErrandDetailSelect(@PathVariable("errandIdx") Long errandIdx) {
        return responseService.getSuccessResult();
    }

    /**
     * 심부름 보내기
     * @return getSuccessResult 전송
     * @author 배태현, 전지환
     */
    @PostMapping("/send")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "RefreshToken", value = "로그인 성공 후 refresh_token", required = false, dataType = "String", paramType = "header")
    })
    public CommonResult sendErrand(@RequestBody ErrandSetDto errandSetDto) throws Exception {
        errandService.sendErrand(errandSetDto);
        return responseService.getSuccessResult();
    }

    /**
     * 심부름을 받은자가 삭제하는 Controller
     * @param errandIdx
     * @return
     * @author 배태현
     */
    @DeleteMapping("/recipient/{errandIdx}")
    public CommonResult deleteByReceiver(@PathVariable("errandIdx") Long errandIdx) {
        return responseService.getSuccessResult();
    }
}
