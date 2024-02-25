package signlanguage.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "wrong_answers")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WrongAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_card")
    private ECard card;

    // đáp án mặt sua
    @Column(nullable = false, length = 100)
    private String back; // đáp án mặt sau.
}
