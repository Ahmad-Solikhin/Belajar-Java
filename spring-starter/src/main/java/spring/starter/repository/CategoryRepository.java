package spring.starter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.starter.domain.Category;
import spring.starter.dto.category.CategoryQuery;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    public Optional<Category> findByCode(String code);

    public Page<Category> findByNameLikeIgnoreCase(String categoryName, Pageable pageable);

    public List<Category> findByCodeIn(List<String> codes);

    /**
     * Ini juga masih agak bingugn sebenernya
     * Tapi kayaknya query ini cuma ngambil id buku sama code dari kategorynya dengan cara join table
     * Trus nanti hasilnya ditampung ke dto CategoryQuery di dalam list
     * Jadi nanti contoh isinya kayak gini [0 => [bookId => 1, categoryCode => a], 1 => [bookId => 1, categoryCode => b]
     * Ini pake JPA Projection, jadi nanti hasil querynya langsung dalam bentuk object
     */
    @Query("SELECT new spring.starter.dto.category.CategoryQuery(b.id, bc.code) " +
            "FROM Book AS b " +
            "JOIN b.categories AS bc " +
            "WHERE b.id IN :bookIdList")
    public List<CategoryQuery> findCategoryByBookIdList(List<Integer> bookIdList);

}
