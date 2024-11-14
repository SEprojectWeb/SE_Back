package com.CompareElec.CompareElec.DTO.Response;

import com.CompareElec.CompareElec.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String userid;
    private String name;
    private String user_type;
    private String phonenumber;

    public UserInfo(User user) {
        this.userid = userid;
        this.name = name;
        this.user_type = user.getUser_type();
        this.phonenumber = user.getPhonenumber();

    }
}
