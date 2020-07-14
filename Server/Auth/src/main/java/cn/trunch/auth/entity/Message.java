package cn.trunch.auth.entity;

public class Message {
    private Integer code;
    private String message;
    private Object data;
    private String checkCode;

    public Message() {
    }

    public Message(Integer code, String message, String checkCode) {
        this.code = code;
        this.message = message;
        this.checkCode = checkCode;
    }

    public Message(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
