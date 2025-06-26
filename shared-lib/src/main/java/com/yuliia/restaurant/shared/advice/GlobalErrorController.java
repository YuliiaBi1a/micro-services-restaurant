package com.yuliia.restaurant.shared.advice;

import com.yuliia.restaurant.shared.dto.ApiError;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorController {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequest(MethodArgumentNotValidException exception) {
        return ApiError.builder()
                .code(400)
                .message(exception.getAllErrors()
                        .stream()
                        .map(error -> error.getObjectName() + "-" + error.getDefaultMessage())
                        .toList().toString()
                ).build();
    }

    @ExceptionHandler({UnsupportedOperationException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleUncaughtException(Exception exception) {
        return ApiError.builder()
                .code(500)
                .message("Uncaught Error: " + exception.getMessage())
                .build();
    }

}