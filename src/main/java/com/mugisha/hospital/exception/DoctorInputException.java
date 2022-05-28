package com.mugisha.hospital.exception;

public class DoctorInputException extends Exception{
    public DoctorInputException() {
        super();
    }
    public DoctorInputException(String message) {
        super(message);
    }

    public DoctorInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoctorInputException(Throwable cause) {
        super(cause);
    }

    protected DoctorInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
