package com.mugisha.hospital.exception;

public class DoctorExistException extends Exception{
    public DoctorExistException() {
        super();
    }

    public DoctorExistException(String message) {
        super(message);
    }

    public DoctorExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoctorExistException(Throwable cause) {
        super(cause);
    }

    protected DoctorExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
