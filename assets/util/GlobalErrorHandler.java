@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public EcpApiResponse<Object> onHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new EcpApiResponse<>(CommonErrorCode.INVALID_INPUT_DATA);
    }

}

// @ExceptionHandler 處理異常的注解，當Controller中的方法拋出異常時ExceptionHandler能有一個統一的方式來處理這些異常，而不是讓他們直接傳到客戶端
// 在Controller的某個方法使用，可以提供一個統一的地方來處理異常，而不是在每個可能拋出異常的地方都處理。

// @ResponseStatus 指定Controller方法的 HttpResponse的狀態碼 (指定OK再回傳自訂的errorcode)