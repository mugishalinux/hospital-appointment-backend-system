package com.mugisha.hospital.exception;

public class DoctorNotFoundException extends Exception{

    public DoctorNotFoundException() {
        super();
    }

    public DoctorNotFoundException(String message) {
        super(message);
    }

    public DoctorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoctorNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DoctorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
