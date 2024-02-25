package signlanguage.dto.carduser;

import lombok.*;
import signlanguage.dto.CardDto;
import signlanguage.entity.CardUser;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardUserDto {
    private Integer id;
    private CardDto card;
    private Boolean isFavoriteCard;
    private Boolean isRemembered;
    private  SetCardDto setCard;


    public CardUserDto(CardUser cardUser) {
        this.id = cardUser.getId();
        this.isFavoriteCard = cardUser.getIsFavoriteCard();
        this.isRemembered = cardUser.getIsRemembered();
        this.card = new CardDto(cardUser.getCard());
        this.setCard = new SetCardDto(cardUser.getSetCard());
    }
}
