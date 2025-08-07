package com.matejgeljic.recipes.exception;

public class IngredientNotFoundException extends CustomException {
    public IngredientNotFoundException() {}

    public IngredientNotFoundException(String message) {
        super(message);
    }

    public IngredientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredientNotFoundException(Throwable cause) {
        super(cause);
    }

    public IngredientNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
