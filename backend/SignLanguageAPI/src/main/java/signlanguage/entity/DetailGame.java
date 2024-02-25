package signlanguage.entity;


import jakarta.persistence.*;
import lombok.*;



@Table(name = "detail_games")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailGame {

    // ví dụ khi lấy ra game đó.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_card")
    private ECard card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_game")
    private Game game;



    @Column(nullable = false)
    private Integer selection;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;
}
