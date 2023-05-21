package com.learn_security.api;

import com.learn_security.dto.*;
import com.learn_security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public Object register(@RequestBody RegisterRequest registerRequest){
        try{
            return ResponseEntity.ok(authenticationService.register(registerRequest));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ExceptionDto.of(e));
        }
    }

    @PostMapping("/authenticate")
    public Object authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ExceptionDto.of(e));
        }
    }


}
