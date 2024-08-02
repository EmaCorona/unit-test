package it.corona.unitest.exception;

import it.corona.unitest.model.dto.ErrorMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(PokemonNotFoundException ex) {
        return getErrorMessage(HttpStatus.NOT_FOUND, ex);
    }

    private ResponseEntity<ErrorMessageDto> getErrorMessage(HttpStatus status, PokemonException ex) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setStatusCode(status.value());
        errorMessageDto.setMessage(status.getReasonPhrase());
        errorMessageDto.setDescription(ex.getLocalizedMessage());
        errorMessageDto.setTimestamp(new Date());
        return ResponseEntity.status(errorMessageDto.getStatusCode()).body(errorMessageDto);
    }
}