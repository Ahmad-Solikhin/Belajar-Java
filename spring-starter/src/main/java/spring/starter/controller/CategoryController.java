package spring.starter.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.starter.dto.ResultPaginationResponse;
import spring.starter.dto.category.CategoryAddAndUpdateRequest;
import spring.starter.dto.category.CategoryListResponse;
import spring.starter.service.CategoryService;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    public final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> addAndUpdateCategory(@RequestBody CategoryAddAndUpdateRequest request){
        categoryService.addAndUpdateCategory(request);
        return ResponseEntity.created(URI.create("/v1/category")).build();
    }

    @GetMapping
    public ResponseEntity<ResultPaginationResponse<CategoryListResponse>> getListCategoryPage(
            @RequestParam(name = "pages", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "5") Integer limit,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "categoryName", required = false) String categoryName
    ){

        return ResponseEntity.ok().body(categoryService.getListCategory(page, limit, sortBy, direction, categoryName));
    }
}
