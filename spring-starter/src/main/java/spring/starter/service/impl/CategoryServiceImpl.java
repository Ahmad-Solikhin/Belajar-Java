package spring.starter.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.starter.domain.Category;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.category.CategoryAddAndUpdateRequest;
import spring.starter.dto.category.CategoryListResponse;
import spring.starter.repository.CategoryRepository;
import spring.starter.service.CategoryService;
import spring.starter.util.PaginationUtil;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public void addAndUpdateCategory(CategoryAddAndUpdateRequest request) {
        Category category = categoryRepository.findByCode(request.getCode().toLowerCase())
                .orElse(new Category());

        if (category.getCode() == null){
            category.setCode(request.getCode().toLowerCase());
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);
    }

    @Override
    public ResultPaginationResponse<CategoryListResponse> getListCategory(Integer pages, Integer limit,
                                                                          String sortBy, String direction,
                                                                          String categoryName) {

        categoryName = categoryName == null ? "%" : categoryName + "%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);

        Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);

        List<CategoryListResponse> list = pageResult.stream().map(v -> {
            CategoryListResponse response = new CategoryListResponse();
            response.setCode(v.getCode());
            response.setName(v.getName());
            response.setDescription(v.getDescription());

            return response;
        }).toList();

        return PaginationUtil.createResultPageResponse(list, pageResult.getTotalElements(), (long) pageResult.getTotalPages());
    }
}
