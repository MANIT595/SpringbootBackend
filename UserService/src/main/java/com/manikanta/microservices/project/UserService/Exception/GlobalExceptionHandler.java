package com.manikanta.microservices.project.UserService.Exception;

import com.manikanta.microservices.project.UserService.DTO.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

// Extends ResponseEntityExceptionHandler to override the handleMethodArgumentNotValid to customize the validation error messages
// Used GlobalExceptionHandler to handle the Error responses all over the user microservice
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDTO> handleNoSuchElementException(NoSuchElementException noSuchElementException, WebRequest webRequest){

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                noSuchElementException.getMessage(),
                webRequest.getDescription(false),
                "User Not Found",
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    // normal way to display validation messages
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String,String> errorsMap = new HashMap<>();
//        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
//
//        errorList.forEach((error)->{
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            errorsMap.put(fieldName,message);
//        });
//        return new ResponseEntity<>(errorsMap,HttpStatus.BAD_REQUEST);
//    }

    // Using stream to display validation messages
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
            Map<String,String> errorsMap = fieldErrors
                .stream()
                .collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));

        return new ResponseEntity<>(errorsMap,HttpStatus.BAD_REQUEST);
    }

    // using streams and converted into customized errorDTO
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        List<ErrorDTO> errorDTOS = ex.getBindingResult().getAllErrors()
//                .stream()
//                        .map((error)->{
//                            ErrorDTO errorDTO = new ErrorDTO(
//                                    LocalDateTime.now(),
//                                    error.getDefaultMessage(),
//                                    request.getDescription(false),
//                                    ((FieldError)error).getField() +" "+error.getDefaultMessage(),
//                                    HttpStatus.BAD_REQUEST.value()
//                            );
//                            return errorDTO;
//                        }).collect(Collectors.toList());
//
//        return new ResponseEntity<>(errorDTOS,HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(EmailAlreadyFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleEmailAlreadyFoundException(Exception exception, WebRequest webRequest){

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "BAD REQUEST",
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(Exception exception, WebRequest webRequest){

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "No User Found",
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception exception, WebRequest webRequest){

        ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
