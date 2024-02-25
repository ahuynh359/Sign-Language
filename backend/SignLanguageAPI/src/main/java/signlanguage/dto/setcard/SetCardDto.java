package signlanguage.dto.setcard;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import signlanguage.entity.SetCard;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetCardDto {
    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private List<CardUserDto> cardUsers = new ArrayList<>();

    public SetCardDto(SetCard setCard) {
        this.id =  setCard.getId();
        this.name = setCard.getName();
        this.createdAt = setCard.getCreatedAt();

        setCard.getCardUsers().forEach(cardUser -> {
            CardUserDto cardUserDto = new CardUserDto(cardUser);
            this.cardUsers.add(cardUserDto);
        });
    }
}
