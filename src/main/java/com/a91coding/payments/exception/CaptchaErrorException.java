package com.a91coding.payments.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CaptchaErrorException extends AuthenticationException {
    /**
     * Creates a new AuthenticationException.
     */
    public CaptchaErrorException() {
        super();
    }

    /**
     * Constructs a new AuthenticationException.
     *
     * @param message the reason for the exception
     */
    public CaptchaErrorException(String message) {
        super(message);
    }

    /**
     * Constructs a new AuthenticationException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public CaptchaErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new AuthenticationException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public CaptchaErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
