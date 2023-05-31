package com.gayuh.restfulapi.service.address;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.AddressResponse;
import com.gayuh.restfulapi.model.CreateAddressRequest;
import com.gayuh.restfulapi.model.UpdateAddressRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressService {
    @Transactional
    public AddressResponse create(User user, CreateAddressRequest request);

    @Transactional(readOnly = true)
    public AddressResponse get(User user, String contactId, String addressId);

    @Transactional
    public AddressResponse update(User user, UpdateAddressRequest request);

    @Transactional
    public void remove(User user, String contactId, String addressId);
    @Transactional(readOnly = true)
    public List<AddressResponse> getAddresses(User user, String contactId);
}
