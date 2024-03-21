package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.AuthRequest;
import pl.coderslab.charity.entity.UserInfo;
import pl.coderslab.charity.repository.UserInfoRepository;
import pl.coderslab.charity.service.JwtService;
import pl.coderslab.charity.service.UserInfoService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

     @PostMapping("/addUser")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }

    }

    @GetMapping("/getUsers")
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    @GetMapping("/getUsers/{id}")
    public Optional<UserInfo> getUser(@PathVariable Long id) {
        return userInfoService.getUser(id);
    }

}

