package ru.hse.amaltheateam.wallets.web.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class, CategoryNotFoundException.class})
    protected ResponseEntity<?> defaultExceptionHandler(RuntimeException ex, WebRequest request) {
        log.error(ExceptionUtils.getStackTrace(ex));

        final ExceptionDetailsDTO apiErrorDTO = ExceptionDetailsDTO.builder()
                .timestamp(Instant.now().getEpochSecond())
                .path(request.getContextPath())
                .message(ex.toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorDTO);
    }
}
//    @ExceptionHandler(value = {NonUniqueUserPhoneException.class})
//    protected ResponseEntity<Object> handleError(NonUniqueUserPhoneException ex, WebRequest request) {
//        logger.error("Exception is occurred", ex);
//        return handleExceptionInternal(ex, ApiErrorResponse.builder()
//                .errorCode("VALIDATION_ERROR")
//                .errorMessage("Phone is already exists")
//                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//
//    }

//    @NonNull
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  @NonNull HttpHeaders headers,
//                                                                  @NonNull HttpStatus status,
//                                                                  @NonNull WebRequest request) {
//        List<ApiErrorResponse> errors = new ArrayList<>(ex.getFieldErrors().size());
//        for (FieldError error : ex.getFieldErrors()) {
//            errors.add(ApiErrorResponse.builder()
//                    .errorCode(HttpStatus.BAD_REQUEST.name())
//                    .errorMessage(String.format("Поле %s - %s", error.getField(), error.getDefaultMessage()))
//                    .build());
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(errors);
//    }
//}
