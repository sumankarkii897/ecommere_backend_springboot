package com.sumankarki.Ecommerce.security;

import com.sumankarki.Ecommerce.entity.User;
import com.sumankarki.Ecommerce.exception.NotFoundException;
import com.sumankarki.Ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByEmail(username)
                .orElseThrow(()-> new NotFoundException("User Email Not found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
