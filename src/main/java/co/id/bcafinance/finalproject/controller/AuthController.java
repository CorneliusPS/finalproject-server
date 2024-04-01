package co.id.bcafinance.finalproject.controller;

import co.id.bcafinance.finalproject.dto.auth.LoginDTO;
import co.id.bcafinance.finalproject.dto.auth.OtpDto;
import co.id.bcafinance.finalproject.dto.auth.RegisterDTO;
import co.id.bcafinance.finalproject.model.User;
import co.id.bcafinance.finalproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth0")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    BindingResult bindingResult;

    @PostMapping("/v1/regis")
    public ResponseEntity<Object> doRegis(@Valid @RequestBody RegisterDTO regisDTO, HttpServletRequest request) {
        User user = modelMapper.map(regisDTO, new TypeToken<User>() {}.getType());

        return userService.checkRegis(user,request);
    }

    @PostMapping("/login/v1")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        User user = modelMapper.map(loginDTO,new TypeToken<User>(){}.getType());
        return userService.doLogin(user,request);
    }

    @PostMapping("/otp/v1")
    public ResponseEntity<Object> otp(@Valid @RequestBody OtpDto otpDto, @RequestHeader("Authorization") String authorizationHeader, HttpServletRequest request) {
        User user = modelMapper.map(otpDto, new TypeToken<User>() {}.getType());

        return userService.verifyOtp(user, authorizationHeader, request);
    }
}


