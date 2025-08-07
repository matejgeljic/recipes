package com.matejgeljic.recipes.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ErrorDto> handleRecipeNotFoundException(RecipeNotFoundException e) {
        log.error("Caught RecipeNotFoundException", e);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Recipe not found");
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecipeUpdateException.class)
    public ResponseEntity<ErrorDto> handleRecipeUpdateException(RecipeUpdateException e) {
        log.error("Caught RecipeUpdateException", e);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Recipe update failed");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<ErrorDto> handleIngredientNotFoundException(IngredientNotFoundException e) {
        log.error("Caught IngredientNotFoundException", e);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Ingredient not found");
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException e) {
        log.error("Caught UserNotFoundException", e);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("User not found");
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Caught MethodArgumentNotValidException", e);
        ErrorDto errorDto = new ErrorDto();

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage
                ).orElse("Validation error occurred");

        errorDto.setError(errorMessage);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Caught ConstraintViolationException", ex);
        ErrorDto errorDto = new ErrorDto();

        String errorMessage = ex.getConstraintViolations()
                .stream()
                .findFirst()
                .map(ConstraintViolation::getMessage
                ).orElse("Constraint violation occurred");

        errorDto.setError(errorMessage);

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        log.error("Caught Exception", e);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("An unknown error occurred");

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
