package signlanguage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import signlanguage.entity.SetCard;


@Repository
public interface SetCardDao extends JpaRepository<SetCard, Integer> {

}