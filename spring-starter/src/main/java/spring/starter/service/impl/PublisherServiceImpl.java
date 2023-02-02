package spring.starter.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.starter.domain.Publisher;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.publisher.PublisherAddRequest;
import spring.starter.dto.publisher.PublisherListResponse;
import spring.starter.dto.publisher.PublisherResponse;
import spring.starter.dto.publisher.PublisherUpdateRequest;
import spring.starter.exception.BadRequestException;
import spring.starter.repository.PublisherRepository;
import spring.starter.service.PublisherService;
import spring.starter.util.PaginationUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    @Override
    public void addPublisher(PublisherAddRequest request) {
        Publisher publisher = new Publisher();
        publisher.setName(request.getPublisherName());
        publisher.setCompanyName(request.getCompanyName());
        publisher.setAddress(request.getAddress());

        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(String publisherId, PublisherUpdateRequest request) {
        Publisher publisher = publisherRepository.findBySecureIdAndDeletedFalse(publisherId)
                .orElseThrow(() -> new BadRequestException("Publisher Not Found"));

        publisher.setName(request.getPublisherName().isBlank() | request.getPublisherName() == null ?
                publisher.getName() : request.getPublisherName());

        publisher.setCompanyName(request.getCompanyName().isBlank() | request.getCompanyName() == null ?
                publisher.getCompanyName() : request.getCompanyName());

        publisher.setAddress(request.getAddress().isBlank() | request.getAddress() == null ?
                publisher.getAddress() : request.getAddress());

        publisherRepository.save(publisher);
    }

    @Override
    public ResultPaginationResponse<PublisherListResponse> findPublisherList(Integer page, Integer limit,
                                                                             String sortBy, String direction,
                                                                             String publisherName) {

        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(page, limit, sort);
        publisherName = StringUtils.isBlank(publisherName) ? "%" : publisherName + "%";
        Page<Publisher> pageResult = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);

        List<PublisherListResponse> result = pageResult.stream().map(v -> {
            PublisherListResponse response = new PublisherListResponse();
            response.setPublisherId(v.getSecureId());
            response.setPublisherName(v.getName());
            response.setCompanyName(v.getCompanyName());

            return response;
        }).toList();

        return PaginationUtil.createResultPageResponse(result, pageResult.getTotalElements(), (long) pageResult.getTotalPages());
    }

    @Override
    public Publisher findPublisher(String publisherId) {

        Publisher publisher = publisherRepository.findBySecureIdAndDeletedFalse(publisherId)
                .orElseThrow(() -> new BadRequestException("Publisher Not Found"));

        return publisher;
    }

    @Override
    public PublisherResponse construct(Publisher publisher) {
        PublisherResponse response = new PublisherResponse();
        response.setPublisherId(publisher.getSecureId());
        response.setPublisherName(publisher.getName());
        return response;
    }
}
