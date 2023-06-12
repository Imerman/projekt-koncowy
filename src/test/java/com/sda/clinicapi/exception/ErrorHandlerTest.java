package com.sda.clinicapi.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ErrorHandlerTest {

    private final ErrorHandler errorHandler = new ErrorHandler();

    @Test
    @SuppressWarnings("all")
    void testHandleUsernameConflict() {
        // given
        String expectedMessage = "Conflict!";
        HttpStatus status = HttpStatus.CONFLICT;
        ConflictException ex = new ConflictException(expectedMessage);

        // when
        ResponseEntity<ErrorResponse> response = errorHandler.handleConflict(ex);
        ErrorResponse actualBody = response.getBody();

        // then
        Assertions.assertEquals(status.value(), actualBody.getCode());
        Assertions.assertEquals(status.getReasonPhrase(), actualBody.getError());
        Assertions.assertEquals(expectedMessage, actualBody.getMessage());
    }

    @Test
    @SuppressWarnings("all")
    void testHandleResourceNotFound() {
        // given
        String expectedMessage = "Not found!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ResourceNotFoundException ex = new ResourceNotFoundException(expectedMessage);

        // when
        ResponseEntity<ErrorResponse> response = errorHandler.handleResourceNotFound(ex);
        ErrorResponse actualBody = response.getBody();

        // then
        Assertions.assertEquals(status.value(), actualBody.getCode());
        Assertions.assertEquals(status.getReasonPhrase(), actualBody.getError());
        Assertions.assertEquals(expectedMessage, actualBody.getMessage());
    }


}