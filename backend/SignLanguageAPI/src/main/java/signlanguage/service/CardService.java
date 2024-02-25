package signlanguage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import signlanguage.dao.CardDao;
import signlanguage.dto.CardDto;
import signlanguage.entity.Category;
import signlanguage.entity.ECard;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardDao cardDao;
    public List<CardDto> getCards() {
        List<ECard> cards = cardDao.findAll();
        return this.transferListEntityToDto(cards);
    }

    public List<CardDto> getCardsFromCategory(Category category) {
        List<ECard> cards = category.getCards();
        return this.transferListEntityToDto(cards);
    }

    private List<CardDto> transferListEntityToDto(List<ECard> cards) {
        List<CardDto> cardDtos = new ArrayList<>();
        cards.forEach(card -> {
            cardDtos.add(new CardDto(card));
        });
        return cardDtos;
    }
}
