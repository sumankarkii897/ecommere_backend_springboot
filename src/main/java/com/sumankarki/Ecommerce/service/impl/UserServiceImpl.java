package com.sumankarki.Ecommerce.service.impl;

import com.sumankarki.Ecommerce.dto.LoginRequest;
import com.sumankarki.Ecommerce.dto.RegisterRequestDto;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.dto.UserDto;
import com.sumankarki.Ecommerce.entity.User;
import com.sumankarki.Ecommerce.enums.UserRole;
import com.sumankarki.Ecommerce.exception.AlreadyExistsException;
import com.sumankarki.Ecommerce.exception.InvalidCredentialException;
import com.sumankarki.Ecommerce.exception.NotFoundException;
import com.sumankarki.Ecommerce.mapper.EntityDtoMapper;
import com.sumankarki.Ecommerce.repository.UserRepository;
import com.sumankarki.Ecommerce.security.JwtUtils;
import com.sumankarki.Ecommerce.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response registerUser(RegisterRequestDto registrationRequest) {
        UserRole role = UserRole.USER;

        if(registrationRequest.getRole() != null && registrationRequest.getRole() == UserRole.ADMIN){
            role = UserRole.ADMIN;

        }
        if(userRepository.existsByEmail(registrationRequest.getEmail())){
            throw new AlreadyExistsException("Email Already Exists");
        }
        User user = User.builder()
                .name(registrationRequest.getName())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .email(registrationRequest.getEmail())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        UserDto userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);

        return Response.builder().status(200)
                .message("User successfully Added")
                .user(userDto)
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new NotFoundException("Email not found"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialException("Invalid Password");
        }

        String token = jwtUtils.generateToken(user);
        return Response.builder()
                .status(200)
                .message("Login successful")
                .token(token)
                .expirationTime("1Day")
                .role(user.getRole().name())
                .build();
    }

    @Override
    public Response getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .message("Successful")
                .userList(userDtos)
                .build();
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        log.info("email : {}", email);
        return userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("User not found"));
    }

    @Override
    public Response getUserInfoAndOrderHistory() {

        User user = getLoginUser();
        UserDto userDto = entityDtoMapper.mapUserToDtoPlusAddressAndOrderItem(user);

        return Response.builder()
                .status(200)
                .message("Successful")
                .user(userDto)
                .build();
    }
}
