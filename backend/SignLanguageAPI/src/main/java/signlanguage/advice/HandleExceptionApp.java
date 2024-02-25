package signlanguage.advice;


import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import signlanguage.dto.ResponseObject;

@RestControllerAdvice
public class HandleExceptionApp {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // các lỗi đều trả ra 401
    public ResponseObject exception(Exception e) {
        return ResponseObject.builder()
                .isSuccess(false)
                .message(e.getMessage())
                .data(null)
                .build();
    }
}
