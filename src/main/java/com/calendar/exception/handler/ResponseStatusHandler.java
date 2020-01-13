package com.calendar.exception.handler;

import com.calendar.exception.BadRequestException;
import com.calendar.exception.InternalServerException;
import com.calendar.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseStatusHandler {
    private static final Logger errorLogger = LogManager.getLogger(ResponseStatusHandler.class);

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<String> badRequestExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity<String> internalServerExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<String> apiNotFoundExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletRequest request, Exception e) throws InternalServerException {
        throw new InternalServerException(e.getMessage());
    }
}
