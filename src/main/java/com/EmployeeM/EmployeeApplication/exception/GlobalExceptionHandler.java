package com.EmployeeM.EmployeeApplication.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Array;
import java.time.LocalDateTime;

@Log4j2
@RestControllerAdvice("com.EmployeeM.EmployeeApplication")
public class GlobalExceptionHandler {

    public ErrorDetails globalExceptionHandler(final Exception ex, final WebRequest request){
     log.error("Internal server Error!"+ ex.getMessage());

     return  new ErrorDetails(
             LocalDateTime.now(),
             ex.getMessage()

     );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDetails resourceNotFoundException( final  ResourceNotFoundException ex, final WebRequest request){
        return  new ErrorDetails(

                LocalDateTime.now(),
                ex.getMessage()
        );

    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceExistException.class)
    public ErrorDetails resourceExitsException(final ResourceExistException ex,final WebRequest request){
        return new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage()
        );
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoContentException.class)
    public ErrorDetails noContentException(final NoContentException ex,final WebRequest request) {
        return new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage()
        );
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public ErrorDetails invalidRequestException(final InvalidRequestException ex,final WebRequest request){
        return new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage()
        );
    }
    public ErrorDetails javaNullPointerException(final NullPointerException ex , WebRequest request){
        log.error("NullPointerException! " + ex.getMessage() );
        log.error("Cause " + ex.getCause());
        String message = ex.getMessage()==null? "Internal Server Value Error" : ex.getMessage();
       return new ErrorDetails(LocalDateTime.now(),message);
    }
}
