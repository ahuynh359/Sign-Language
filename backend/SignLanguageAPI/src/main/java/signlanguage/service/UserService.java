package signlanguage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import signlanguage.dao.UserDao;
import signlanguage.dto.UserDto;
import signlanguage.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    // lấy user ra khỏi contextHolder.




    public UserDto registerUser(UserDto userDto) throws Exception {
        Optional<User>  oUser = userDao.findById(userDto.getUid());
        if (oUser.isPresent()) throw new Exception("Đã tồn tại user!");
        User user = User.builder()
                .uid(userDto.getUid())
                .email(userDto.getEmail())
                .typeAccount(userDto.getTypeAccount())
                .build();
        try {
            return new UserDto(userDao.save(user));
        }
        catch(Exception e) {
            throw new Exception(e);
        }
    }



}
