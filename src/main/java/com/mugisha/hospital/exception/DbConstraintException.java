package com.mugisha.hospital.exception;

public class DbConstraintException extends Exception {
    public DbConstraintException() {
        super();
    }

    public DbConstraintException(String message) {
        super(message);
    }

    public DbConstraintException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbConstraintException(Throwable cause) {
        super(cause);
    }

    protected DbConstraintException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
