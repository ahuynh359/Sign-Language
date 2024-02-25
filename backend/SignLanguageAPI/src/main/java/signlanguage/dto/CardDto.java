package signlanguage.dto;

import lombok.*;
import signlanguage.entity.ECard;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Integer id;
    private String front;
    private String back;
    private String category;

    public CardDto(ECard card) {
        this.id = card.getId();
        this.front = card.getFront();
        this.back = card.getBack();
        this.category = card.getCategory().getName();
    }
}
