package com.learn_security.service;

import com.learn_security.dto.CustomerInfoRequest;
import com.learn_security.dto.CustomerInfoResponse;
import com.learn_security.models.User;
import com.learn_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    public CustomerInfoResponse getInfo(CustomerInfoRequest customerInfoRequest) {
        String email = jwtService.extractUsername(customerInfoRequest.getToken());
        System.out.println("email: " + email);
        User user = userRepository.findByEmail(email).get();
        return CustomerInfoResponse.builder()
                .address(user.getAddress())
                .name(user.getName())
                .telephoneNumber(user.getTelephoneNumber())
                .email(email)
                .build();
    }
}
