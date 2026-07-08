package com.sumankarki.Ecommerce.service.impl;

import com.sumankarki.Ecommerce.dto.AddressDto;
import com.sumankarki.Ecommerce.dto.Response;
import com.sumankarki.Ecommerce.entity.Address;
import com.sumankarki.Ecommerce.entity.User;
import com.sumankarki.Ecommerce.repository.AddressRepository;
import com.sumankarki.Ecommerce.service.interf.AddressService;
import com.sumankarki.Ecommerce.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    @Override
    public Response saveAndUpdateAddress(AddressDto addressDto) {

        User user = userService.getLoginUser();
        List<Address> addressList = user.getAddress();
     if(addressList == null){
        addressList = new ArrayList<>();
        user.setAddress(addressList);
     }
     Address address = new Address();

     address.setUser(user);

        if (addressDto.getStreet() != null)
            address.setStreet(addressDto.getStreet());

        if (addressDto.getCity() != null)
            address.setCity(addressDto.getCity());

        if (addressDto.getState() != null)
            address.setState(addressDto.getState());

        if (addressDto.getZipCode() != null)
            address.setZipCode(addressDto.getZipCode());

        if (addressDto.getCountry() != null)
            address.setCountry(addressDto.getCountry());

        addressList.add(address);

        addressRepository.save(address);

        String message = (user.getAddress() == null ? "Address successfully saved" : "Address successfully updated");

        return Response.builder()
                .status(200)
                .message(message)
                .build();
    }
}
