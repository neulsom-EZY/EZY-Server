package com.server.EZY.model.user;

import com.server.EZY.model.user.enumType.Permission;
import com.server.EZY.model.user.enumType.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.EnumType.*;

@Entity @Table(name = "User")
@Builder @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userIdx;

    @Column(name = "NickName", nullable = false, unique = true)
//    @Size(min = 1, max = 10)
    private String nickname;

    @Column(name = "Password", nullable = false)
//    @Size(min = 4, max = 10)
    private String password;

    @Column(name = "PhoneNumber", nullable = false, unique = true)
//    @Size(min = 11, max = 11)
    private String phoneNumber;

    @Column(name = "Permission", nullable = false)
    @Enumerated(value = STRING)
    private Permission permission;

    @Enumerated(STRING) @Column(name = "Role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "UserIdx"))
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // List<Role> 형태를 Stream을 사용하여 roles 원소의 값을 String으로 바꿔주는 Enum.name()을 이용하여 List<String>형태로 변환(GrantedAuthority의 생성자는 String 타입을 받기 때문)
        List<String> rolesConvertString = this.roles.stream().map(Enum::name).collect(Collectors.toList());
        return rolesConvertString.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.nickname;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname != null ? nickname : this.nickname;
    }

    public void updatePassword(String password) {
        this.password = password != null ? password : this.password;
    }
}
