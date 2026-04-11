package com.filltex.price_table.dto;

import com.filltex.price_table.domain.Member;
import com.filltex.price_table.domain.MemberRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private String loginId;
    private String password;
    private String name;

    public Member toEntity() {
        return new Member(loginId, password, name, MemberRole.USER);
    }

}
