package signlanguage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import signlanguage.dto.ResponseObject;
import signlanguage.dto.UserDto;
import signlanguage.service.UserService;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    @PostMapping("/auth/register")
    public ResponseObject registerUser(@RequestBody UserDto userDto) throws Exception {
        return ResponseObject
                .builder()
                .isSuccess(true)
                .message("Truy vấn thành công!")
                .data(userService.registerUser(userDto))
                .build();
    }
}
