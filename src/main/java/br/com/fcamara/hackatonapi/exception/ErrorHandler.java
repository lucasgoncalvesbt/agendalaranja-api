package br.com.fcamara.hackatonapi.exception;

import br.com.fcamara.hackatonapi.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CapacityExceededException.class)
    public ResponseEntity<ResponseErrorDTO> capacityExceededException(CapacityExceededException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseErrorDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(EmailAlreadyScheduledForThisDayException.class)
    public ResponseEntity<ResponseErrorDTO> emailAlreadyScheduledForThisDayException(EmailAlreadyScheduledForThisDayException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseErrorDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> notFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseErrorDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(SchedulingExceededException.class)
    public ResponseEntity<ResponseErrorDTO> schedulingExceededException(SchedulingExceededException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseErrorDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(EmailAlreadyHasAnAccountException.class)
    public ResponseEntity<ResponseErrorDTO> emailAlreadyHasAnAccount(EmailAlreadyHasAnAccountException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseErrorDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(UserDoesNotHavePermissionException.class)
    public ResponseEntity<ResponseErrorDTO> userDoesNotHavePermissionException(UserDoesNotHavePermissionException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseErrorDTO.builder().message(e.getMessage()).build());
    }
}
