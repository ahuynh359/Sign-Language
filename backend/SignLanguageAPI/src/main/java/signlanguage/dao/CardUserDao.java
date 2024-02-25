package signlanguage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import signlanguage.entity.CardUser;

@Repository
public interface CardUserDao extends JpaRepository<CardUser, Integer> {
}
