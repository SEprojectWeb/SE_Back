package com.CompareElec.CompareElec.DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    private String userid;
    private String password;
    private String name;
    private String phoneNumber;
    private String userType;
}
