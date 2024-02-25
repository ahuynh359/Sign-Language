package signlanguage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import signlanguage.entity.ECard;


@Repository
public interface CardDao extends JpaRepository<ECard, Integer> {
    // lấy ra theo category.
    // cho phép lọc theo name.



}
