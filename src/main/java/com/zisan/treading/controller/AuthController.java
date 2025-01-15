package com.zisan.treading.controller;

import com.zisan.treading.config.JwtProvider;
import com.zisan.treading.modal.TwoFactorOTP;
import com.zisan.treading.modal.User;
import com.zisan.treading.repository.UserRepository;
import com.zisan.treading.response.AuthResponse;
import com.zisan.treading.service.CustomeUserDetailsService;
import com.zisan.treading.service.EmailService;
import com.zisan.treading.service.TwoFactorOtpService;
import com.zisan.treading.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {

        User isEmailExist = userRepository.findByEmail(user.getEmail());

        if(isEmailExist != null){
            throw new Exception("email is already used with another account");
        }


        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());

        User savedUser = userRepository.save(newUser);

        Authentication auth=new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt= JwtProvider.generateToken(auth);

        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("User registered successfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {

        String userName=user.getEmail();
        String password=user.getPassword();




        Authentication auth=authenticate(userName,password);

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt= JwtProvider.generateToken(auth);

        User authUser=userRepository.findByEmail(userName);
        if(user.getTwoFactorAuth().isEnabled()){
            AuthResponse res=new AuthResponse();
            res.setMessage("Two factor authentication is enabled.");
            res.setTwoFactorAuthEnabled(true);
            String otp= OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP=twoFactorOtpService.findByUser(authUser.getId());
            if(oldTwoFactorOTP!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);
            }

            TwoFactorOTP newTwoFactorOTP=twoFactorOtpService.createTwoFactorOtp(authUser,otp,jwt);

            emailService.sendVerificationOtpEmail(userName,otp);

            res.setSession(newTwoFactorOTP.getId());
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }

        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login successfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(userName);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }

        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }


    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySigingOtp(@PathVariable String otp,@RequestParam String id) throws Exception{

        TwoFactorOTP twoFactorOTP = twoFactorOtpService.findById(id);

        if (twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP, otp)) {
            AuthResponse res = new AuthResponse();
            res.setMessage("Two factor authentication verified");
            res.setTwoFactorAuthEnabled(true);
            res.setJwt(twoFactorOTP.getJwt());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new Exception("invalid otp");
        }

    }

}