package com.gayuh.restfulapi.service.contact;

import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.ContactResponse;
import com.gayuh.restfulapi.model.CreateContactRequest;
import com.gayuh.restfulapi.model.SearchContactRequest;
import com.gayuh.restfulapi.model.UpdateContactRequest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ContactService {

    public ContactResponse create(User user, CreateContactRequest request);

    @Transactional(readOnly = true)
    public ContactResponse get(User user, String id);

    @Transactional
    public ContactResponse update(User user, UpdateContactRequest request);
    @Transactional
    public void delete(User user, String contactId);
    @Transactional(readOnly = true)
    public Page<ContactResponse> search(User user, SearchContactRequest request);
}
