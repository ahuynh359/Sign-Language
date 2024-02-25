package signlanguage.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {
    private boolean isSuccess;
    private String message;
    private Object data;

    public ResponseObject(Object data) {
        this.data = data;
        this.message = "Truy vấn thành công!";
        this.isSuccess = true;
    }

}
