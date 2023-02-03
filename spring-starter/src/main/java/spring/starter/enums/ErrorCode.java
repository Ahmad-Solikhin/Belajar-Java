package spring.starter.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    INVALID_DATA(404),
    INTERNAL_ERROR(2),
    NETWORK_ERROR(3),
    OTHER_ERROR(4),
    NOT_FOUND(404);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode(){
        return code;
    }
}
