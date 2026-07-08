package com.sumankarki.Ecommerce.service.interf;

import com.sumankarki.Ecommerce.dto.LoginRequest;
import com.sumankarki.Ecommerce.dto.RegisterRequestDto;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.dto.UserDto;
import com.sumankarki.Ecommerce.entity.User;

public interface UserService {

    Response registerUser(RegisterRequestDto registrationRequest);

    Response loginUser (LoginRequest loginRequest);

    Response getAllUsers();

    User getLoginUser();

    Response getUserInfoAndOrderHistory();
}
