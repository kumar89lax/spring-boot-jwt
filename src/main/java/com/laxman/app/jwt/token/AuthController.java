package com.laxman.app.jwt.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

   /* @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", "USER");
        return jwtUtil.generateToken(username, claims);
    }*/
   @PostMapping("/login")
   public String login(@RequestBody LoginRequest loginRequest) {
       String username = loginRequest.getUsername();
       String password = loginRequest.getPassword();
       System.out.println("Login request for username: " + username);
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
       System.out.println("User authenticated successfully");
       Map<String, Object> claims = new HashMap<>();
       claims.put("roles", "USER");
       return jwtUtil.generateToken(username, claims);
   }


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.saveUser(user);
        return "User registered successfully!";
    }
}
