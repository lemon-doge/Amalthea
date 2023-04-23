package com.tinkoff.sirius.amalthea.repository;

import com.tinkoff.sirius.amalthea.model.Category;
import com.tinkoff.sirius.amalthea.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where (c.user.id = :userId or c.user.id is null)")
    List<Category> findAllByUserId(@Param("userId") Long userId);
}
