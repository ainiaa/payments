package com.a91coding.payments.exception;


public class CaptchaMissingException extends CaptchaErrorException {
    public CaptchaMissingException() {
        super("Captcha Missing Exception");
    }
}
