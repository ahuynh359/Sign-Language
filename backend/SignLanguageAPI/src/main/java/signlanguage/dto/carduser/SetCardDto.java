package signlanguage.dto.carduser;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import signlanguage.entity.SetCard;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetCardDto {
    private Integer id;
    private String name;

    public SetCardDto(SetCard setCard) {
        this.id =  setCard.getId();
        this.name = setCard.getName();
    }
}
