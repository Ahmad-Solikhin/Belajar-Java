package spring.starter.util;

import org.springframework.data.domain.Sort;
import spring.starter.dto.ResultPaginationResponse;

import java.util.List;

public class PaginationUtil {

    public static <T> ResultPaginationResponse<T> createResultPageResponse(List<T> result, Long element, Long page){
        ResultPaginationResponse<T> results = new ResultPaginationResponse<>();
        results.setPage(page);
        results.setElements(element);
        results.setResult(result);

        return results;
    }

    public static Sort.Direction getSortBy(String sortBy){
        return sortBy.equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
