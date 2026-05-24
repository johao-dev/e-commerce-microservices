package zuzz.projects.e_commerce.microservices.customer_microservice.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import zuzz.projects.e_commerce.microservices.common_exceptions.ErrorResponse;
import zuzz.projects.e_commerce.microservices.common_exceptions.GlobalExceptionHandler;

@Primary
@RestControllerAdvice(basePackages = "zuzz.projects.e_commerce.microservices.customer_microservice")
public class CustomerExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(CustomerNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "customer";
        errors.put(fieldName, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errors));
    }
}
