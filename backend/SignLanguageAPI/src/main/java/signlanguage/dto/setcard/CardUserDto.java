package signlanguage.dto.setcard;

import lombok.*;
import signlanguage.dto.CardDto;
import signlanguage.entity.CardUser;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardUserDto {
    private Integer id; // đã có id ở trong đây rồi nè.
    private CardDto card;
    private Boolean isFavoriteCard;
    private Boolean isRemembered;

    public CardUserDto(CardUser cardUser) {
        this.id = cardUser.getId();
        this.isFavoriteCard = cardUser.getIsFavoriteCard();
        this.isRemembered = cardUser.getIsRemembered();
        this.card = new CardDto(cardUser.getCard());
    }
}
