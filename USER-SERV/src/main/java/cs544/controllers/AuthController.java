package cs544.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs544.dto.LoginRequest;
import cs544.dto.LoginResponse;
import cs544.services.JwtUtilityService;
import cs544.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    // @Autowired
    // private UserDetailsService userDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtilityService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest user) {
        // var userDetail = userDetailService.loadUserByUsername(user.username());
        // var password = userDetail.getPassword();
        // if (!passwordEncoder.matches(user.password(), password)) {
        // throw new BadCredentialsException("Invalid username or password");
        // }
        // String[] roleNames = userDetail.getAuthorities().stream().map(a ->
        // a.getAuthority()).toArray(String[]::new);
        // var token = jwtService.generateToken(user.username(), roleNames);
        // var response = new LoginResponse(token);
        // return ResponseEntity.ok(response);

        var foundUser = userService.getUserByUsername(user.username());
        if (foundUser == null || !passwordEncoder.matches(user.password(), foundUser.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        var token = jwtService.generateToken(foundUser);
        var response = new LoginResponse(token);
        return ResponseEntity.ok(response);
    }
}