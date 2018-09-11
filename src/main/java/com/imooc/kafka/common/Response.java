package com.imooc.kafka.common;

import java.io.Serializable;

public class Response   implements Serializable {

	/**
	 * @date:   2018年9月10日 下午6:34:12   
	 */
	private static final long serialVersionUID = 1L;
	   /**
     * 响应编码
     */
    private int code;
    /**
     * 响应消息
     */
    private String message;

    public Response() {
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
