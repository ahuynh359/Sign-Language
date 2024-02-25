package signlanguage.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import signlanguage.common.Helper;
import signlanguage.dao.CardDao;
import signlanguage.dao.CardUserDao;
import signlanguage.dao.SetCardDao;
import signlanguage.dto.setcard.SetCardDto;
import signlanguage.dto.requestbody.SetCardRQBody;
import signlanguage.entity.CardUser;
import signlanguage.entity.ECard;
import signlanguage.entity.SetCard;
import signlanguage.entity.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SetCardService {
    private final SetCardDao setCardDao;
    private final CardDao cardDao;
    private final CardUserDao cardUserDao;
    private final Helper helper;

    public List<SetCardDto> getSetCards(){ // Lay ra toan bo set card.
        User user = helper.getUser();
        List<SetCard> setCards = user.getSetCards();
        List<SetCardDto> setCardDtos = new ArrayList<>();
        setCards.forEach(setCard -> {
            setCardDtos.add(new SetCardDto(setCard));
        });
        return setCardDtos; // lúc này đang lấy ra toàn bộ => không cần thiết.
    }


    @Transactional
    public SetCardDto createSetCard(SetCardRQBody setCardRQBody) throws Exception {
        User user = helper.getUser(); // lấy ra user hiện tại
        List<Integer> idCards = setCardRQBody.getIdCards();
        List<CardUser> cardUsers = new ArrayList<>();

        SetCard setCard = SetCard.builder()
                .name(setCardRQBody.getName())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        for (Integer idCard : idCards) {
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
            cardUsers.add(cardUser);
        }
        setCard.setCardUsers(cardUsers);
        setCardDao.save(setCard);
        return new SetCardDto(setCard);
    }

    public SetCardDto deleteCard(Integer id) throws Exception {
        SetCard setCard = this.isExistAndPermission(id);
        setCardDao.deleteById(id);
        return new SetCardDto(setCard);
    }

    public SetCard isExistAndPermission(Integer id) throws Exception{
        User user = helper.getUser();
        Optional<SetCard> oSetCard = setCardDao.findById(id);
        if (oSetCard.isEmpty()) throw new Exception("Không tồn tại set card!");
        SetCard setCard = oSetCard.get();
        if (!setCard.getUser().getUid().equals(user.getUid())) throw new Exception("Không được phép!");
        return setCard;
    }

    public SetCardDto updateSetCard(Integer id, SetCardRQBody setCardRQBody) throws Exception {
        SetCard setCard = this.isExistAndPermission(id);
        setCard.setName(setCardRQBody.getName());

        return new SetCardDto(setCardDao.save(setCard));
    }

    // thay đổi cũng chỉ thay đổi 1 lần 1 card => hoặc có thể thêm mới vào
    // bây giờ tạo thêm 3 cái. put, post, delete )

    // thẻ đó sẽ có 1 cái dto bao gồm các thông tin Card + thông tin usercard + thông tin setCard.
    // => vẫn có thể update toàn bộ.

    public SetCardDto getSetCardWithId(Integer id) throws Exception {
        Optional<SetCard> oSetCard = setCardDao.findById(id);
        if (oSetCard.isEmpty()) throw new Exception("Không tồn tại set card!");
        SetCard setCard = oSetCard.get();
        return new SetCardDto(setCard);
    }













}
