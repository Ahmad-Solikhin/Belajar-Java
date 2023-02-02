package spring.starter.service;

import spring.starter.domain.Category;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.category.CategoryAddAndUpdateRequest;
import spring.starter.dto.category.CategoryListResponse;

import java.util.List;

public interface CategoryService {

    public void addAndUpdateCategory(CategoryAddAndUpdateRequest request);

    public ResultPaginationResponse<CategoryListResponse> getListCategory(Integer pages, Integer limit,
                                                                          String sortBy, String direction,
                                                                          String categoryName);

    public List<Category> findCategories(List<String> categoryCodeList);

    public List<CategoryListResponse> construct(List<Category> categories);

}
