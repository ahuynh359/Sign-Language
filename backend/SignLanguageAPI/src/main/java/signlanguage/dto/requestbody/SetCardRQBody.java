package signlanguage.dto.requestbody;

import lombok.*;

import java.util.List;

@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class SetCardRQBody {
    private String name;
    private List<Integer> idCards;
}
