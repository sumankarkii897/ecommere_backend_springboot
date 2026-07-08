package com.sumankarki.Ecommerce.controller;


import com.sumankarki.Ecommerce.dto.LoginRequest;
import com.sumankarki.Ecommerce.dto.RegisterRequestDto;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.dto.UserDto;
import com.sumankarki.Ecommerce.service.interf.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
private final UserService userService;

@PostMapping("/register")
public ResponseEntity<Response> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto){

    return ResponseEntity.ok(userService.registerUser(registerRequestDto));


}

@PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequestDto){
    return ResponseEntity.ok(userService.loginUser(loginRequestDto));
}

}
