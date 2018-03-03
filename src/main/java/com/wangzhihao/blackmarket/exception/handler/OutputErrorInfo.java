package com.wangzhihao.blackmarket.exception.handler;


class OutputErrorInfo {
    private String message;
    private String code;
    private String traceId;
    private String type;

    public OutputErrorInfo(String message, String code, String traceId, String type) {
        this.message = message;
        this.code = code;
        this.traceId = traceId;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
