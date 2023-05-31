package com.gayuh.restfulapi.controller;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.AddressResponse;
import com.gayuh.restfulapi.model.CreateAddressRequest;
import com.gayuh.restfulapi.model.UpdateAddressRequest;
import com.gayuh.restfulapi.model.WebResponse;
import com.gayuh.restfulapi.service.address.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class AddressController {

    private AddressService addressService;

    @PostMapping(
            value = "/api/contacts/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<AddressResponse>> create(User user,
                                                               @RequestBody CreateAddressRequest request,
                                                               @PathVariable("contactId") String contactId
    ) {
        request.setContactId(contactId);
        AddressResponse response = addressService.create(user, request);
        return ResponseEntity.ok(WebResponse
                .<AddressResponse>builder()
                .data(response)
                .build());
    }

    @GetMapping(
            value = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<AddressResponse>> get(User user,
                                                            @PathVariable("contactId") String contactId,
                                                            @PathVariable("addressId") String addressId
    ) {
        AddressResponse response = addressService.get(user, contactId, addressId);

        return ResponseEntity.ok(WebResponse
                .<AddressResponse>builder()
                .data(response)
                .build());
    }

    @PutMapping(
            value = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<AddressResponse>> update(User user,
                                                               @RequestBody UpdateAddressRequest request,
                                                               @PathVariable("contactId") String contactId,
                                                               @PathVariable("addressId") String addressId
    ) {
        request.setContactId(contactId);
        request.setAddressId(addressId);

        AddressResponse response = addressService.update(user, request);

        return ResponseEntity.ok(WebResponse
                .<AddressResponse>builder()
                .data(response)
                .build());
    }

    @DeleteMapping(
            value = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> remove(User user,
                                                      @PathVariable("contactId") String contactId,
                                                      @PathVariable("addressId") String addressId
    ) {

        addressService.remove(user, contactId, addressId);

        return ResponseEntity.ok(WebResponse
                .<String>builder()
                .data("Oke")
                .build());
    }

    @GetMapping(
            value = "/api/contacts/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<AddressResponse>>> getAddresses(User user,
                                                                           @PathVariable("contactId") String contactId
    ) {
        List<AddressResponse> responses = addressService.getAddresses(user, contactId);

        return ResponseEntity.ok(WebResponse
                .<List<AddressResponse>>builder()
                .data(responses)
                .build());
    }

}
