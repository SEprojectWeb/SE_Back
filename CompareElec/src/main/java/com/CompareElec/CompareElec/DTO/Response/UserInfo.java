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
        this.userid = user.getUserid();
        this.name = user.getName();
        this.user_type = user.getUserType();
        this.phonenumber = user.getPhonenumber();

    }
}
