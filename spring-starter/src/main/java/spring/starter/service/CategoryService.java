package spring.starter.service;

import spring.starter.domain.Category;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.category.CategoryAddAndUpdateRequest;
import spring.starter.dto.category.CategoryListResponse;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    public void addAndUpdateCategory(CategoryAddAndUpdateRequest request);

    public ResultPaginationResponse<CategoryListResponse> getListCategory(Integer pages, Integer limit,
                                                                          String sortBy, String direction,
                                                                          String categoryName);

    public List<Category> findCategories(List<String> categoryCodeList);

    public List<CategoryListResponse> construct(List<Category> categories);

    //Integer di sini adalah book id, List<String> nya merupakan category bukunya
    public Map<Integer, List<String>> findCategoriesMap(List<Integer> bookIdList);

}
