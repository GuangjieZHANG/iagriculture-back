package com.huanshi.model;

public class ErrorMessage {

    private String errorCode;
    private String message;
    private String detail;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorCode, String message, String detail) {
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
    }

    public String getErrorCode(){
        return this.errorCode;
    }

    public String getMessage(){
        return this.message;
    }

    public String getDetail(){
        return this.detail;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"errorCode\": \"" + this.getErrorCode() + "\",\n" +
                "\"message\": \"" + this.getMessage() + "\",\n" +
                "\"detail\": \"" + this.getDetail() + "\"\n" +
                "}";
    }

}
