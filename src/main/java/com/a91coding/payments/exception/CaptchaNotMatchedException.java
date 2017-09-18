package com.a91coding.payments.exception;

public class CaptchaNotMatchedException extends CaptchaErrorException {
    public CaptchaNotMatchedException() {
        super("Captcha Not Matched Exception");
    }
}
