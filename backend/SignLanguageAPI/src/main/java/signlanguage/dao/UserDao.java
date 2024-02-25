package signlanguage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import signlanguage.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

}
