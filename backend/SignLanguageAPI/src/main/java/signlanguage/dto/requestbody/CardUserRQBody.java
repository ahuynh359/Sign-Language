package signlanguage.dto.requestbody;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardUserRQBody {
    private Boolean isFavoriteCard;
    private Boolean isRemembered;
    private Integer idSetCard;
}


