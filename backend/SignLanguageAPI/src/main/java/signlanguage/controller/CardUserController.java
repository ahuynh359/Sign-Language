package signlanguage.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import signlanguage.dto.ResponseObject;
import signlanguage.dto.requestbody.CardUserRQBody;
import signlanguage.dto.requestbody.LCardUserRQBody;
import signlanguage.dto.setcard.SetCardDto;
import signlanguage.service.CardUserService;


@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CardUserController {

    private final CardUserService cardUserService;

    @PutMapping("/card-users/{id}")
    public ResponseObject updateCardUsers(@PathVariable Integer id,@RequestBody CardUserRQBody cardUserRQBody) throws Exception {
        return new ResponseObject(cardUserService.updateCardUser(id,cardUserRQBody));
    }

    @DeleteMapping("/card-users/{id}")
    public ResponseObject deleteCardUser(@PathVariable Integer id) throws Exception {
        return new ResponseObject(cardUserService.deleteCardUser(id));
    }

    @PostMapping("/card-users") // them nhieu the card.
    public ResponseObject addUserCard(@RequestBody LCardUserRQBody lCardUserRQBody) throws Exception {
        return new ResponseObject(cardUserService.addCardUserIntoSetCard(lCardUserRQBody));
    }
}
