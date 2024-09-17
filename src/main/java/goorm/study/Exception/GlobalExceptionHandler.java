package goorm.study.Exception;

import goorm.code.ErrorCode;
import goorm.study.dto.response.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //Not null size 해당하는 유효성 검사
    /**
     * 입력값 검증
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(ErrorCode.BAD_REQUEST.getStatus().value())
                .body(new ErrorResponseDTO(ErrorCode.BAD_REQUEST, errors));
    }
    @ExceptionHandler(InvalidHostException.class)
    protected ResponseEntity<ErrorResponseDTO> handleInvalidHostException(final InvalidHostException e) {
        return ResponseEntity
                .status(ErrorCode.UNABLE_TO_RESOLVE_HOST.getStatus().value())
                .body(new ErrorResponseDTO(ErrorCode.UNABLE_TO_RESOLVE_HOST));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<ErrorResponseDTO> handleUserAlreadyExistsException(final UserAlreadyExistsException e) {
        return ResponseEntity
                .status(ErrorCode.USER_ALREADY_EXISTS.getStatus().value())
                .body(new ErrorResponseDTO(ErrorCode.USER_ALREADY_EXISTS));
    }



}
