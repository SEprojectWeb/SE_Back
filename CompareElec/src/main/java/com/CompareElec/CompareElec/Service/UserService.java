package com.CompareElec.CompareElec.Service;

import com.CompareElec.CompareElec.DTO.User.UserCreateRequest;
import com.CompareElec.CompareElec.DTO.JWT.JwtToken;
import com.CompareElec.CompareElec.DTO.JWT.JwtTokenProvider;
import com.CompareElec.CompareElec.DTO.Response.UserInfo;
import com.CompareElec.CompareElec.DTO.User.UserInfoRequest;
import com.CompareElec.CompareElec.DTO.User.findIdDTO;
import com.CompareElec.CompareElec.DTO.User.findPasswdDTO;
import com.CompareElec.CompareElec.domain.User;
import com.CompareElec.CompareElec.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void saveUser(UserCreateRequest request) {
        Optional<User> existingUser = userRepository.findByUserid(request.getUserid());

        //이미 있는 id인지 확인
        if (existingUser.isPresent()) {
            System.out.println("User id already exists");
            return;
        }
        User user = new User(request.getUserid(), request.getPassword(), request.getName(), request.getPhoneNumber(),request.getUserType(), Collections.singletonList("User"));
        System.out.println("Saving user" + request.getUserid());
        System.out.println("Saving user" + user.getUserid());

        userRepository.save(user);

    }

    @Transactional
    public JwtToken signIn(String userid, String password) {
        // 1. userid + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userid, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 User 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        User user = userRepository.findByUserid(userid).orElse(null);

        System.out.println("id :"+user.getUserid());
        System.out.println("pw :"+user.getPassword());
        System.out.println("name :"+user.getUsername());
        System.out.println("phonenum :"+user.getPhonenumber());
        System.out.println("userType :"+user.getUserType());

        jwtToken.setUserType(user.getUserType());
        return jwtToken;
    }

    @Transactional
    public UserInfo getUserInfo(String userid) {
        User user = userRepository.findByUserid(userid).orElse(null);
        return new UserInfo(user);
    }

    @Transactional
    public void deleteUser(String userid){
        System.out.println("Deleting user: " + userid);

        User user = userRepository.findByUserid(userid).orElse(null);

        System.out.println("Deleting user: " + user.getUserid());

        userRepository.delete(user);
    }

    @Transactional
    public String findUserid(findIdDTO findDTO){
        String name = findDTO.getName();
        String phonenumber = findDTO.getPhonenumber();

        User user = userRepository.findByNameAndPhonenumber(name,phonenumber)
                    .orElse(null);

        if(user == null){
            System.out.println("User not found");
            return null;
        }

        return user.getUserid();
    }

    @Transactional
    public String findPassword(findPasswdDTO findDTO){
        String userid = findDTO.getUserid();
        String name = findDTO.getName();
        String phonenumber = findDTO.getPhonenumber();

        User user = userRepository.findByUseridAndNameAndPhonenumber(userid,name,phonenumber)
                .orElse(null);

        if(user == null){
            System.out.println("User not found");
            return null;
        }
        return user.getPassword();
    }

    // 사용자 정보 수정
    @Transactional
    public UserInfo updateUserInfo(String userid, UserInfoRequest userInfoRequest) {
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        user.setName(userInfoRequest.getName());
        user.setPhonenumber(userInfoRequest.getPhonenumber());
        userRepository.save(user);
        return new UserInfo(user);
    }

}
