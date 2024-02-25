package signlanguage.dto.requestbody;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LCardUserRQBody {
    private List<Integer> idCards;
    private Integer idSetCard;
}
