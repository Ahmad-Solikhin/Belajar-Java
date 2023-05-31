package com.gayuh.restfulapi.controller;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.*;
import com.gayuh.restfulapi.service.contact.ContactService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/contacts")
@AllArgsConstructor
public class ContactController {

    private ContactService contactService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<ContactResponse>> create(User user, @RequestBody CreateContactRequest request) {

        ContactResponse response = contactService.create(user, request);

        return ResponseEntity.created(URI.create("/contacts")).body(WebResponse
                .<ContactResponse>builder()
                .data(response)
                .build());
    }

    @GetMapping(
            value = "{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<ContactResponse>> get(
            User user,
            @PathVariable("contactId") String id
    ) {
        ContactResponse response = contactService.get(user, id);

        return ResponseEntity.ok(WebResponse
                .<ContactResponse>builder()
                .data(response)
                .build());
    }

    @PutMapping(
            value = "{contactId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<ContactResponse>> update(User user,
                                                               @RequestBody UpdateContactRequest request,
                                                               @PathVariable("contactId") String id) {

        request.setId(id);

        ContactResponse response = contactService.update(user, request);

        return ResponseEntity.ok(WebResponse
                .<ContactResponse>builder()
                .data(response)
                .build());
    }

    @DeleteMapping(
            value = "{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> delete(
            User user,
            @PathVariable("contactId") String id
    ) {
        contactService.delete(user, id);

        return ResponseEntity.ok(WebResponse
                .<String>builder()
                .data("Oke")
                .build());
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<List<ContactResponse>>> search(User user,
                                                                     @RequestParam(value = "name", required = false) String name,
                                                                     @RequestParam(value = "email", required = false) String email,
                                                                     @RequestParam(value = "phone", required = false) String phone,
                                                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(value = "size", required = false, defaultValue = "10") Integer seize) {
        SearchContactRequest request = SearchContactRequest.builder()
                .page(page)
                .size(seize)
                .name(name)
                .email(email)
                .phone(phone)
                .build();

        Page<ContactResponse> responses = contactService.search(user, request);
        return ResponseEntity.ok(WebResponse
                .<List<ContactResponse>>builder()
                .data(responses.getContent())
                .paging(PagingResponse.builder()
                        .currentPage(responses.getNumber())
                        .totalPage(responses.getTotalPages())
                        .size(request.getSize())
                        .build())
                .build());
    }

}
