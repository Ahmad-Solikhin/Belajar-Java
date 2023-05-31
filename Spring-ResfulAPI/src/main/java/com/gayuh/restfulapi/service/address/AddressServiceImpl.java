package com.gayuh.restfulapi.service.address;

import com.gayuh.restfulapi.entity.Address;
import com.gayuh.restfulapi.entity.Contact;
import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.AddressResponse;
import com.gayuh.restfulapi.model.CreateAddressRequest;
import com.gayuh.restfulapi.model.UpdateAddressRequest;
import com.gayuh.restfulapi.repository.AddressRepository;
import com.gayuh.restfulapi.repository.ContactRepository;
import com.gayuh.restfulapi.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;
    private ContactRepository contactRepository;
    private ValidationService validationService;

    @Override
    public AddressResponse create(User user, CreateAddressRequest request) {
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        Address address = new Address();
        address.setContact(contact);
        address.setCity(request.getCity());
        address.setCountry(request.getCountry());
        address.setProvince(request.getProvince());
        address.setStreet(request.getStreet());
        address.setPostalCode(request.getPostalCode());

        addressRepository.save(address);

        return toAddressResponse(address);
    }

    @Override
    public AddressResponse get(User user, String contactId, String addressId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not FOund"));

        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        return toAddressResponse(address);
    }

    @Override
    public AddressResponse update(User user, UpdateAddressRequest request) {
        validationService.validate(request);
        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        Address address = addressRepository.findFirstByContactAndId(contact, request.getAddressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        address.setProvince(request.getProvince());
        address.setCountry(request.getCountry());
        address.setStreet(request.getStreet());
        address.setPostalCode(request.getPostalCode());
        address.setCity(request.getCity());

        addressRepository.save(address);

        return toAddressResponse(address);
    }

    @Override
    public void remove(User user, String contactId, String addressId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        addressRepository.delete(address);
    }

    @Override
    public List<AddressResponse> getAddresses(User user, String contactId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        List<Address> addresses = addressRepository.findAllByContact(contact);

        return addresses.stream().map(this::toAddressResponse).toList();
    }

    private AddressResponse toAddressResponse(Address address){
        return AddressResponse.builder()
                .street(address.getStreet())
                .id(address.getId())
                .city(address.getCity())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .province(address.getProvince())
                .build();
    }
}
