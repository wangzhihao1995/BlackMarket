package com.wangzhihao.blackmarket.exception;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/10/24.
 *
 * @author Wang Zhihao.
 */

public class GeneralException extends RuntimeException {
    private String message; //NOSONAR
    private String code; //NOSONAR
    private String traceId; //NOSONAR
    private String type; //NOSONAR

    public GeneralException(String message) {
        super(message);
        this.message = message;
    }

    public GeneralException(Exception e) {
        super(e.getMessage());
    }

    @Override
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
