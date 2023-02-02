package spring.starter.service;

import spring.starter.domain.Publisher;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.publisher.PublisherAddRequest;
import spring.starter.dto.publisher.PublisherListResponse;
import spring.starter.dto.publisher.PublisherResponse;
import spring.starter.dto.publisher.PublisherUpdateRequest;

public interface PublisherService {

    public void addPublisher(PublisherAddRequest request);

    public void updatePublisher(String publisherId, PublisherUpdateRequest request);

    public ResultPaginationResponse<PublisherListResponse> findPublisherList(Integer page, Integer limit,
                                                                             String sortBy, String direction,
                                                                             String publisherName);

    public Publisher findPublisher(String publisherId);

    public PublisherResponse construct(Publisher publisher);
}
