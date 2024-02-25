package signlanguage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import signlanguage.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, String> {
    Optional<Category> findByName(String name);
}
