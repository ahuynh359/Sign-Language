package signlanguage.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "cards")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ECard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String front;

    @Column(length = 100, nullable = false)
    private String back;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private Category category;


    @OneToMany(mappedBy = "card",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<WrongAnswer> wrongAnswers;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CardUser> cardUsers;

    @OneToMany(mappedBy = "card",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DetailGame> detailGames;

}
