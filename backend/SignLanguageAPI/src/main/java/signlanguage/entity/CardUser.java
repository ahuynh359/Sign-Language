package signlanguage.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "cards_user")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_card")
    private ECard card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_set_card")
    private SetCard setCard;

    @Column(name ="is_favorite_card")
    private Boolean isFavoriteCard;

    @Column(name = "is_remembered")
    private Boolean isRemembered;
}
