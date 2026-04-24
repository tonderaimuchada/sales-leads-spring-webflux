/*
 * @author Tonderai Muchada
 * @date 23-04-2026 17:57
 */

package za.co.instacom.salesleads.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import za.co.instacom.salesleads.dto.AuthDto;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AuthDto.ErrorResponse> handleRuntimeException(
            RuntimeException ex, ServerWebExchange exchange) {
        log.error("Runtime exception: {}", ex.getMessage());

        HttpStatus status = ex.getMessage().contains("not found")
                ? HttpStatus.NOT_FOUND
                : ex.getMessage().contains("credentials")
                ? HttpStatus.UNAUTHORIZED
                : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(AuthDto.ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(exchange.getRequest().getPath().toString())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<AuthDto.ErrorResponse> handleValidationException(
            WebExchangeBindException ex, ServerWebExchange exchange) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AuthDto.ErrorResponse.builder()
                .status(400)
                .error("Validation Failed")
                .message(errors)
                .path(exchange.getRequest().getPath().toString())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthDto.ErrorResponse> handleGenericException(
            Exception ex, ServerWebExchange exchange) {
        log.error("Unhandled exception: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(AuthDto.ErrorResponse.builder()
                .status(500)
                .error("Internal Server Error")
                .message("An unexpected error occurred")
                .path(exchange.getRequest().getPath().toString())
                .timestamp(LocalDateTime.now())
                .build());
    }
}
