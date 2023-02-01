package spring.starter.service;

import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.category.CategoryAddAndUpdateRequest;
import spring.starter.dto.category.CategoryListResponse;

public interface CategoryService {

    public void addAndUpdateCategory(CategoryAddAndUpdateRequest request);

    public ResultPaginationResponse<CategoryListResponse> getListCategory(Integer pages, Integer limit,
                                                                          String sortBy, String direction,
                                                                          String categoryName);

}
