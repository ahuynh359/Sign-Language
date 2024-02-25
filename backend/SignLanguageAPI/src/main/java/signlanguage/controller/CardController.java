package signlanguage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import signlanguage.dao.CategoryDao;
import signlanguage.dto.CardDto;
import signlanguage.dto.ResponseObject;
import signlanguage.entity.Category;
import signlanguage.service.CardService;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CardController {
    private final CardService cardService;
    private final CategoryDao categoryDao;

    @GetMapping("/cards")
    public ResponseObject getCards(@RequestParam(required = false) String category) throws Exception {
        if (category != null) {
            Optional<Category> oCategory = categoryDao.findByName(category);
            if (oCategory.isEmpty()) throw new Exception("Không tồn tại category!");
            List<CardDto> cardDtos = cardService.getCardsFromCategory(oCategory.get());
            return new ResponseObject(cardDtos);
        } else {
            List<CardDto> cardDtos = cardService.getCards();
            return new ResponseObject(cardDtos);
        }
    }
}
