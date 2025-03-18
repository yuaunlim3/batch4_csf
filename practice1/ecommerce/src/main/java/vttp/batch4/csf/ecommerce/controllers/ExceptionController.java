package vttp.batch4.csf.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vttp.batch4.csf.ecommerce.models.Exception.ApiError;
import vttp.batch4.csf.ecommerce.models.Exception.FailedException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(FailedException.class)
    public ResponseEntity<ApiError> handleException(FailedException ex, HttpServletRequest request,
            HttpServletResponse response) {
        ApiError apiError = new ApiError(404, ex.getMessage());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }
}
