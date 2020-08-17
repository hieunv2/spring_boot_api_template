package com.artech.api.respository;

import com.artech.api.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT c FROM Category c"
            + " WHERE (c.name LIKE %:searchValue% OR c.description LIKE %:searchValue%)")
    Page<Category> findBySearchValue(@Param("searchValue") String searchValue, Pageable pageable);
    Long countByStatus(Integer status);

    @Query("SELECT c FROM Category c  WHERE c.status = 1")
    List<Category> getCategoryByStatus();
}
