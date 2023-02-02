package spring.starter.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.publisher.PublisherAddRequest;
import spring.starter.dto.publisher.PublisherListResponse;
import spring.starter.dto.publisher.PublisherUpdateRequest;
import spring.starter.service.PublisherService;

import javax.naming.Name;
import java.net.URI;

@RestController
@RequestMapping("api/v1/publishers")
@AllArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<Void> addPublisher(@RequestBody @Valid PublisherAddRequest request){
        publisherService.addPublisher(request);

        return ResponseEntity.created(URI.create("/v1/publisher")).build();
    }

    @PutMapping("{publisherId}")
    public ResponseEntity<Void> updatePublisher(@PathVariable("publisherId") String secureId
            , @RequestBody PublisherUpdateRequest request){
        publisherService.updatePublisher(secureId, request );

        return ResponseEntity.ok().build();
    }

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
