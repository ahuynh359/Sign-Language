package signlanguage.dto;

import lombok.*;
import signlanguage.entity.User;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String uid;
    private String email;
    private String typeAccount;

    public UserDto(User user) {
        this.uid = user.getUid();
        this.email = user.getEmail();
        this.typeAccount = user.getTypeAccount();
    }

}
