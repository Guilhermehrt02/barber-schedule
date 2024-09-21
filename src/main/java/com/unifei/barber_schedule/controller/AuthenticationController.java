package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.domain.user.AuthenticationDTO;
import com.unifei.barber_schedule.domain.user.RegisterDTO;
import com.unifei.barber_schedule.domain.user.User;
import com.unifei.barber_schedule.repository.UserRepository;
import com.unifei.barber_schedule.service.UserFactoryManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserFactoryManager userFactoryManager;
//    private TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UserRepository userRepository,
                                    UserFactoryManager userFactoryManager
//                                    ,TokenService tokenService
                                    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userFactoryManager = userFactoryManager;
//        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

//        var token = tokerService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {

        if(this.userRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        try {

            User newUser = userFactoryManager.createUser(data, encryptedPassword);
            userRepository.save(newUser);

            return ResponseEntity.ok("User registered successfully.");
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
