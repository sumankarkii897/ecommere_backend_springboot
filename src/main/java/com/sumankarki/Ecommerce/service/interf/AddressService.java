package com.sumankarki.Ecommerce.service.interf;

import com.sumankarki.Ecommerce.dto.AddressDto;
import com.sumankarki.Ecommerce.dto.Response;

public interface AddressService {

    Response saveAndUpdateAddress(AddressDto address);
}
