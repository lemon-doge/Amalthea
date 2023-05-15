package ru.hse.amaltheateam.wallets.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hse.amaltheateam.wallets.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where (c.userId is null or c.userId = :userId)")
    List<Category> findAllByUserId(@Param("userId") Long userId);
}
