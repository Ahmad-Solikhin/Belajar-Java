package com.gayuh.restfulapi.service.contact;

import com.gayuh.restfulapi.entity.Contact;
import com.gayuh.restfulapi.entity.User;
import com.gayuh.restfulapi.model.ContactResponse;
import com.gayuh.restfulapi.model.CreateContactRequest;
import com.gayuh.restfulapi.model.SearchContactRequest;
import com.gayuh.restfulapi.model.UpdateContactRequest;
import com.gayuh.restfulapi.repository.ContactRepository;
import com.gayuh.restfulapi.service.ValidationService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService{

    private ContactRepository contactRepository;
    private ValidationService validationService;

    @Override
    public ContactResponse create(User user, CreateContactRequest request) {
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setUser(user);
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());

        contactRepository.save(contact);

        return this.toContactResponse(contact);
    }

    @Override
    public ContactResponse get(User user, String id) {
        Contact contact = contactRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Mot Found"));

        return this.toContactResponse(contact);
    }

    @Override
    public ContactResponse update(User user, UpdateContactRequest request) {
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());

        contactRepository.save(contact);

        return toContactResponse(contact);
    }

    @Override
    public void delete(User user, String contactId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found"));

        contactRepository.delete(contact);
    }

    @Override
    public Page<ContactResponse> search(User user, SearchContactRequest request) {
        Specification<Contact> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("user"), user));
            if (request.getName() != null){
                predicates.add(builder.or(
                        builder.like(root.get("firstName"), "%" + request.getName() + "%"),
                        builder.like(root.get("lastName"), "%" + request.getName() + "%")
                ));
            }

            if (request.getEmail() != null){
                predicates.add(builder.like(root.get("email"), "%" + request.getEmail() + "%"));
            }

            if (request.getPhone() != null){
                predicates.add(builder.like(root.get("phone"), "%" + request.getPhone() + "%"));
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Contact> contacts = contactRepository.findAll(specification, pageable);

        List<ContactResponse> contactResponses = contacts.getContent().stream()
                .map(this::toContactResponse)
                .toList();

        return new PageImpl<>(contactResponses, pageable, contacts.getTotalElements());
    }

    private ContactResponse toContactResponse(Contact contact) {
        return ContactResponse.builder()
                .email(contact.getEmail())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .phone(contact.getPhone())
                .id(contact.getId())
                .build();
    }

}
