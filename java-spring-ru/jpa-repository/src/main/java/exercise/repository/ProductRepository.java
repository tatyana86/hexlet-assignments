package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    Optional<Product> findByTitleOrPrice(String title, Integer price);
    List<Product> findByTitleAndPrice(String title, Integer price);

    List<Product> findByPriceLessThan(Integer price);
    List<Product> findByPriceLessThanEqual(Integer price);

    List<Product> findByPriceBetween(Integer min, Integer max, Sort sort);
    // END
}
