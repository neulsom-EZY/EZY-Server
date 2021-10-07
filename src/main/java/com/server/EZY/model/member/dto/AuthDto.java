package com.server.EZY.model.member.dto;

import com.server.EZY.model.member.enum_type.Role;
import com.server.EZY.model.member.MemberEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collections;

@Getter @Setter
@NoArgsConstructor
@Builder
public class AuthDto {

    @NotBlank
    @Pattern(regexp = "^@[a-zA-Z]*$")
    @Size(min = 1, max = 10)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    public AuthDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .username(this.getUsername())
                .password(this.getPassword())
                .roles(Collections.singletonList(Role.ROLE_CLIENT))
                .build();
    }
}
