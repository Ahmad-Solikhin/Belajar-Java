package com.gayuh.webmvc.controller;

import com.gayuh.webmvc.model.Partner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartnerController {

    @GetMapping("/partner/current")
    public ResponseEntity<String> getPartner(Partner partner){
        return ResponseEntity.ok(partner.getId() + " : " + partner.getName());
    }

}
