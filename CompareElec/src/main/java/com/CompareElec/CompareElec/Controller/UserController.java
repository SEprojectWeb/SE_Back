package com.CompareElec.CompareElec.Controller;

import com.CompareElec.CompareElec.DTO.User.SignInDto;
import com.CompareElec.CompareElec.DTO.User.UserCreateRequest;
import com.CompareElec.CompareElec.DTO.JWT.JwtToken;
import com.CompareElec.CompareElec.DTO.JWT.JwtTokenProvider;
import com.CompareElec.CompareElec.DTO.Response.UserInfo;
import com.CompareElec.CompareElec.DTO.User.findIdDTO;
import com.CompareElec.CompareElec.DTO.User.findPasswdDTO;
import com.CompareElec.CompareElec.Service.UserService;
import com.CompareElec.CompareElec.domain.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "회원가입,로그인 API")
public class UserController {
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public UserController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "회원가입 API")
    @PostMapping(value = "/regist")
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @Operation(summary = "로그인 API")
    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String username = signInDto.getUserid();
        String password = signInDto.getPassword();
        JwtToken jwtToken = userService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }


    @GetMapping("/get-userinfo")
    @Operation(summary = "access 토큰에서 사용자 정보 가져오기",security = {@SecurityRequirement(name = "accessToken")})
    public UserInfo getUserInfo(Authentication authentication) {
        String userid = ((User) authentication.getPrincipal()).getUsername();
        System.out.println(userid);
        return userService.getUserInfo(userid);
    }

    @DeleteMapping("/delete-user")
    @Operation(summary = "access 토큰에서 사용자 정보 가져와서 삭제하기",security = {@SecurityRequirement(name = "accessToken")})
    public void deleteUser(Authentication authentication) {
        String userid = ((User) authentication.getPrincipal()).getUsername();
        userService.deleteUser(userid);
    }


    @GetMapping("/findUserid")
    @Operation(summary = "이름,전화번호 이용해서 아이디 찾기")
    public String findUserid(@RequestBody findIdDTO findDTO) {
        return userService.findUserid(findDTO);
    }

    @GetMapping("/findPassword")
    @Operation(summary = "아이디,이름,전화번호 이용해서 비밀번호 찾기")
    public String findPassword(@RequestBody findPasswdDTO findDTO) {
        return userService.findPassword(findDTO);
    }


}