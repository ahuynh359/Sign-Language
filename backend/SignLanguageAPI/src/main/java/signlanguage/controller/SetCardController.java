package signlanguage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import signlanguage.dto.ResponseObject;
import signlanguage.dto.setcard.SetCardDto;
import signlanguage.dto.requestbody.SetCardRQBody;
import signlanguage.service.SetCardService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
@ResponseStatus(HttpStatus.OK)
public class SetCardController {

    private final SetCardService setCardService;

    @GetMapping("/set-cards")
    public ResponseObject getSetCards(){
        List<SetCardDto> setCardDtos = setCardService.getSetCards();
        return new ResponseObject(setCardDtos);
    }

    @PostMapping("/set-cards")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject createSetCard(@RequestBody SetCardRQBody setCardRQBody) throws Exception {
        SetCardDto setCardDto = setCardService.createSetCard(setCardRQBody);
        return new ResponseObject(setCardDto);
    }

    @PutMapping("set-cards/{id}")
    public ResponseObject updateSetCard(@PathVariable Integer id,
                                        @RequestBody SetCardRQBody setCardRQBody) throws Exception {
        SetCardDto setCardDto = setCardService.updateSetCard(id, setCardRQBody);
        return new ResponseObject(setCardDto);
    }

    @DeleteMapping("set-cards/{id}")
    public ResponseObject deleteCard(@PathVariable Integer id) throws Exception {
        SetCardDto setCardDto = setCardService.deleteCard(id);
        return new ResponseObject(setCardDto);
    }


    // chưa co get.
    @GetMapping("set-cards/{id}")
    public ResponseObject getSetCardWithId(@PathVariable Integer id) throws Exception {
        return new ResponseObject(setCardService.getSetCardWithId(id));
    }


//    @GetMapping("/set-cards/{id}")
//    public ResponseObject getSetCard(@PathVariable Integer id) throws Exception {
//        SetCardAndCards setCardAndCards = setCardService.getSetCard(id);
//        return new ResponseObject(setCardAndCards);
//    }



//    @PostMapping("/set-cards/{id}/cards")
//    public ResponseObject addCardIntoSetCard() {
//        return null;
//    }

//    @DeleteMapping("/set-cards/{id}/cards/{id-card}")
//    public ResponseObject deleteCardFromSetCard() {
//        return null;
//    }
}
