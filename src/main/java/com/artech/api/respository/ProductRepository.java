package com.artech.api.respository;

import com.artech.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p"
            + " WHERE (p.name LIKE %:searchValue% OR p.description LIKE %:searchValue%)")
    Page<Product> findBySearchValue(@Param("searchValue") String searchValue, Pageable pageable);
    Long countByStatus(Integer status);
}
