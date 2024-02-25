package signlanguage.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.stereotype.Service;
import signlanguage.dao.CardDao;
import signlanguage.dao.CardUserDao;
import signlanguage.dao.SetCardDao;
import signlanguage.dto.carduser.CardUserDto;
import signlanguage.dto.requestbody.CardUserRQBody;
import signlanguage.dto.requestbody.LCardUserRQBody;
import signlanguage.dto.setcard.SetCardDto;
import signlanguage.entity.CardUser;
import signlanguage.entity.ECard;
import signlanguage.entity.SetCard;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardUserService {

    private final CardUserDao cardUserDao;
    private final SetCardDao setCardDao;
    private final CardDao cardDao;

    public CardUserDto updateCardUser(Integer id,CardUserRQBody cardUserRQBody) throws Exception {
        Integer idSetCard = cardUserRQBody.getIdSetCard();
        Optional<SetCard> oSetCard = setCardDao.findById(idSetCard);
        Optional<CardUser> oCardUser = cardUserDao.findById(id);
        if (oSetCard.isEmpty()) throw new Exception("Không tồn tại set card!");
        if (oCardUser.isEmpty()) throw new Exception("Không tồn tại usercard!");
        CardUser cardUser = oCardUser.get();
        SetCard setCard = oSetCard.get();
        cardUser.setIsFavoriteCard(cardUserRQBody.getIsFavoriteCard());
        cardUser.setIsRemembered(cardUserRQBody.getIsRemembered());
        cardUser.setSetCard(setCard);
        return new CardUserDto(cardUserDao.save(cardUser));
    }

//    public CardUserDto deleteCardUser(Integer id) throws Exception {
//        Optional<CardUser> oCardUser = cardUserDao.findById(id);
//        if (oCardUser.isEmpty()) {
//            throw new Exception("Không tồn tại usercard!");
//        }
//        CardUser cardUser = oCardUser.get();
//        cardUserDao.delete(cardUser);
//        return new CardUserDto(cardUser);
//    }

    // tiep theo la cong viec them card vao set-cards.

    @Transactional
    public SetCardDto addCardUserIntoSetCard(LCardUserRQBody lCardUserRQBody) throws Exception {

        Integer idSetCard = lCardUserRQBody.getIdSetCard();

        Optional<SetCard> oSetCard = setCardDao.findById(idSetCard);
        if (oSetCard.isEmpty()) {
            throw new Exception("Không tồn tại usercard!");
        }
        SetCard setCard = oSetCard.get();
        // da co ton tai => them vao.
        List<Integer> idCards = lCardUserRQBody.getIdCards();



        for (Integer idCard : idCards) {// tim kiem card. neu khong tim thay thi khong them vao./
            Optional<ECard> oCard = cardDao.findById(idCard);
            if (oCard.isEmpty()) {
                String message = "Không tồn tài card với id: " + idCard;
                throw new Exception(message);
            }
            CardUser cardUser = CardUser.builder()
                    .card(oCard.get())
                    .setCard(setCard)
                    .isFavoriteCard(false)
                    .isRemembered(false).build();
            setCard.getCardUsers().add(cardUser);
        }
        return new SetCardDto(setCardDao.save(setCard));
    }

    public CardUserDto deleteCardUser(Integer id) throws Exception {
        Optional<CardUser> oCardUser = cardUserDao.findById(id);
        if (oCardUser.isEmpty()) {
            throw new Exception("Không tồn tại usercard!");
        }
        cardUserDao.delete(oCardUser.get());
        return new CardUserDto(oCardUser.get());
    }
}
