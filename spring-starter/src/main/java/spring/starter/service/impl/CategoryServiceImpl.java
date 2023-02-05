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
import spring.starter.dto.category.CategoryQuery;
import spring.starter.exception.BadRequestException;
import spring.starter.repository.CategoryRepository;
import spring.starter.service.CategoryService;
import spring.starter.util.PaginationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Category> findCategories(List<String> categoryCodeList) {
        List<Category> categories = categoryRepository.findByCodeIn(categoryCodeList);
        if (categories.isEmpty()) throw new BadRequestException("Category not found");

        return categories;
    }

    @Override
    public List<CategoryListResponse> construct(List<Category> categories) {
        return categories.stream().map(v -> {
            CategoryListResponse response = new CategoryListResponse();
            response.setCode(v.getCode());
            response.setName(v.getName());
            response.setDescription(v.getDescription());
            return response;
        }).toList();
    }

    @Override
    public Map<Integer, List<String>> findCategoriesMap(List<Integer> bookIdList) {

        //Ini masih rada pusing sih

        //Ini dipake buat nampung dulu hasil query objectnya
        List<CategoryQuery> queryList = categoryRepository.findCategoryByBookIdList(bookIdList);

        //Ini buat bikin map nya
        Map<Integer, List<String>> categoryMap = new HashMap<>();

        //Bikin List kosong dulu
        List<String> categoryCodes;

        //loop buat queryList
        for (var v : queryList){
            //Dicek apakah map nya udah ada key dari bookId dari queryList apa belum
            if (!categoryMap.containsKey(v.getBookId())){
                //Kalo belum list kosong nya dibuat array list yang kosong
                categoryCodes = new ArrayList<>();
            }else {
                //Kalo udah ada list nya di map maka akan diambil list yang lama
                categoryCodes = categoryMap.get(v.getBookId());
            }

            //Lalu code categorynya dimasukin kedalam list, entah dia list baru atau list lama
            categoryCodes.add(v.getCategoryCode());

            //Nah habis itu dimasukiin ke map deh dengan key bookId nya dan value nya itu list dari code nya tadi
            //Kenapa demikian karena ia pake put, dimana kalo udah ada key nya maka akan ketimbun dan valuenya akan nambah karena manggil list yang lama kalo nimbun
            categoryMap.put(v.getBookId(), categoryCodes);
        }

        return categoryMap;
    }
}
