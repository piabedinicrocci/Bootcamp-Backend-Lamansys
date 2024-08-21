package ar.lamansys.messages;

import ar.lamansys.messages.application.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MessageExceptionHandler {

    @ExceptionHandler({UserNotExistsException.class, UserSessionNotExists.class, ProductNotExistsException.class})
    public ResponseEntity<Map<String, String>> notExistsHandler(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", "NOT_FOUND");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Map<String, String>> existsHandler(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", "BAD_REQUEST");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CartUserNotExistsException.class)
    public ResponseEntity<Map<String, String>> cartUserNotExistHandler(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", "CART_MISMATCH");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OpenCartException.class)
    public ResponseEntity<Map<String, String>> openCartHandler(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", "OPEN_CART_ALREADY_EXIST");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StockNotAvailableException.class)
    public ResponseEntity<Map<String, String>> stockNotAvailableHandler(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", "INSUFFICIENT_STOCK");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName=((FieldError) error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotInCartException.class)
    public ResponseEntity<Map<String, String>> productNotInCartHandler(ProductNotInCartException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("code", "PRODUCT_IN_CART_MISMATCH");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
