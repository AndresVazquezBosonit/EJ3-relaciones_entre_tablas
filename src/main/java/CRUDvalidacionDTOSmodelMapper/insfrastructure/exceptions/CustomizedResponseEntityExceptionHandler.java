package CRUDvalidacionDTOSmodelMapper.insfrastructure.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)

    public final ResponseEntity<CustomError> handleNotFoundException(NotFoundException notFoundException, WebRequest webRequest) {

        CustomError customError = new CustomError
                (
                        new Date(),
                        HttpStatus.NOT_FOUND.value(),
                        notFoundException.getMessage()
                );


        return new ResponseEntity<CustomError>(customError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errorMessages = ex.getBindingResult()
                                       .getAllErrors()
                                       .stream()
                                       .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                       .collect(Collectors.toList());

        CustomError customError = new CustomError
                (
                        new Date(),
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        errorMessages.size() < 2 ? errorMessages.get(0) : errorMessages.toString().replace("]", "").replace("[", "")
                );

        return new ResponseEntity<Object>(customError, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
