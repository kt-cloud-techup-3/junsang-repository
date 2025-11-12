package com.kt.shopping.common.api;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Hidden
@RestControllerAdvice
public class ApiAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse.ErrorData> internalServerError(Exception e) {
        e.printStackTrace();
        // 서버에러입니다
        return ApiErrorResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러입니다. 백엔드팀에 문의하세요.");
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiErrorResponse.ErrorData> customException(CustomException e) {
        return ApiErrorResponse.error(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse.ErrorData> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        var details = Arrays.toString(e.getDetailMessageArguments());
        var message = details.split(",", 2)[1].replace("]", "").trim();

        return ApiErrorResponse.error(HttpStatus.BAD_REQUEST, message);
    }
}
