package com.learn_security.google;


import com.learn_security.models.User;
import com.learn_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        System.out.println(email);
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            User customer = User.builder()
                    .email(email)
                    .name(oAuth2User.getName())
                    .address("ha noi")
                    .telephoneNumber("0123441231")
                    .build();
            userRepository.save(customer);
        }



    }
}
