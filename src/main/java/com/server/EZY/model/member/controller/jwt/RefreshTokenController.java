package com.server.EZY.model.member.controller.jwt;

import com.server.EZY.model.member.service.jwt.RefreshTokenService;
import com.server.EZY.response.ResponseService;
import com.server.EZY.response.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final ResponseService responseService;

    @GetMapping("/refreshtoken")
    @ResponseStatus( HttpStatus.OK )
    public SingleResult<Map<String, String>> refresh(HttpServletRequest request) {
        Map<String, String> map = refreshTokenService.getRefreshToken(request);
        return responseService.getSingleResult(map);
    }
}
