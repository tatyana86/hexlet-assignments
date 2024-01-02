package exercise.specification;

import exercise.dto.ProductDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

import java.time.LocalDate;

import static org.springframework.data.jpa.domain.AbstractAuditable_.createdDate;

// BEGIN
@Component // Для возможности автоматической инъекции
public class ProductSpecification {
    // Генерация спецификации на основе параметров внутри DTO
    // Для удобства каждый фильтр вынесен в свой метод
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()))
                .and(withTitleCont(params.getTitleCont()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null
                ? cb.conjunction()
                : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withTitleCont(String substring) {
        return (root, query, cb) -> substring == null
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("title")), "%" + substring + "%");
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return (root, query, cb) -> priceGt == null ? cb.conjunction() : cb.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, cb) -> ratingGt == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), ratingGt);
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, cb) -> priceLt == null ? cb.conjunction() : cb.lessThan(root.get("price"), priceLt);
    }

}
// END
