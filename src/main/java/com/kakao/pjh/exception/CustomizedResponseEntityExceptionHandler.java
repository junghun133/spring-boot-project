package com.kakao.pjh.exception;

import com.kakao.pjh.data.ResultComponent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ResultComponent.Result.INTERNAL_SERVER_ERROR.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //404
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ResultComponent.Result.NOT_FOUND.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    //403
    @ExceptionHandler(MapDataNotFoundException.class)
    public final ResponseEntity<Object> handleMapDataNotFoundExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ResultComponent.Result.NOT_FOUND.getMessage(),  request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    //401
    @ExceptionHandler(AccessDeniedAPIKeyException.class)
    public final ResponseEntity<Object> handleAPIKeyNotFoundExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ResultComponent.Result.UNAUTHORIZED.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse,  HttpStatus.UNAUTHORIZED);
    }

    //204
    @ExceptionHandler(NoResultWithHistoryKeywordException.class)
    public final ResponseEntity<Object> handleNoResultWithHistoryKeywordExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ResultComponent.Result.NO_CONTENT.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse,  HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ArgumentNotValidData argumentNotValidData = new ArgumentNotValidData(HttpStatus.BAD_REQUEST, ResultComponent.Result.BAD_REQUEST.getMessage(), errorList);
        return handleExceptionInternal(ex, argumentNotValidData, headers, argumentNotValidData.getStatus(), request);
    }
}
