package com.learn_security.api;

import com.learn_security.dto.CustomerInfoRequest;
import com.learn_security.dto.CustomerInfoResponse;
import com.learn_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class CustomerController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CustomerInfoResponse> getInfoCustomer(@RequestBody CustomerInfoRequest token){

        return new ResponseEntity<>(userService.getInfo(token), HttpStatus.OK);
    }

    @GetMapping
    public Principal user(Principal principal){
        return principal;
    }
}
