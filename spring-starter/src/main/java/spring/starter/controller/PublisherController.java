package spring.starter.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.publisher.PublisherAddRequest;
import spring.starter.dto.publisher.PublisherListResponse;
import spring.starter.dto.publisher.PublisherUpdateRequest;
import spring.starter.service.PublisherService;

import javax.naming.Name;
import java.net.URI;

//Annotation untuk validasi di path variable
@Validated
@RestController
@RequestMapping("api/v1/publishers")
@AllArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    //PreAuthorize digunakan untuk menentukan apakah dia harus login dan apakah dia harus diakses oleh role tertentu
    @PreAuthorize("hasAnyRole('admin', 'user')")
    @PostMapping
    public ResponseEntity<Void> addPublisher(@RequestBody @Valid PublisherAddRequest request){
        publisherService.addPublisher(request);

        return ResponseEntity.created(URI.create("/v1/publisher")).build();
    }
    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("{publisherId}")
    public ResponseEntity<Void> updatePublisher(@PathVariable("publisherId") @Size(max = 36, min = 36, message = "not UUID") String secureId
            , @RequestBody PublisherUpdateRequest request){
        publisherService.updatePublisher(secureId, request );

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<ResultPaginationResponse<PublisherListResponse>> getListPublisherPage(
            @RequestParam(name = "pages", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "5") Integer limit,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "publisherName", required = false) String publisherName
    ){

        return ResponseEntity.ok().body(publisherService.findPublisherList(page, limit, sortBy, direction, publisherName));
    }

}
