package com.matejgeljic.recipes.exception;

public class RecipeUpdateException extends CustomException {
    public RecipeUpdateException() {}

    public RecipeUpdateException(String message) {
        super(message);
    }

    public RecipeUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipeUpdateException(Throwable cause) {
        super(cause);
    }

    public RecipeUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
