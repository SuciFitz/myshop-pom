package com.sucifitz.myshop.commons.dto;

import java.io.Serializable;

/**
 * 自定义返回结果
 * @author Sucifitz
 * @create 2020/3/29 16:57
 */
public class BaseResult implements Serializable {

    private int status;
    private String message;

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    public static BaseResult success() {
        return BaseResult.createResult(STATUS_SUCCESS, "success");
    }

    public static BaseResult success(String message) {
        return BaseResult.createResult(STATUS_SUCCESS, message);
    }

    public static BaseResult fail() {
        return BaseResult.createResult(STATUS_FAIL, "fail");
    }

    public static BaseResult fail(String message) {
        return BaseResult.createResult(STATUS_FAIL, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static BaseResult createResult(int status, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }
}