package me.catas.cabin.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    /**
     * 业务处理状态
     */
    private Boolean success;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 错误展示类型
     */
    private Integer showType;

    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        BizCode rce = null;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            rce = BizCode.FAILED;
        }
        return result(true, rce, data);
    }

    public static <T> Result<T> failed() {
        return result(false, BizCode.FAILED, null);
    }

    public static <T> Result<T> failed(String msg) {
        return result(false, BizCode.FAILED.getCode(), msg, BizCode.FAILED.getType(), null);
    }

    public static <T> Result<T> failed(BizCode bizCode) {
        return result(false, bizCode, null);
    }

    public static <T> Result<T> status(boolean status) {
        return status ? success() : failed();
    }

    private static <T> Result<T> result(Boolean success, BizCode bizCode, T data) {
        if (bizCode != null) {
            return result(success, bizCode.getCode(), bizCode.getMsg(), bizCode.getType(), data);
        }
        return result(success, null, null, null, data);
    }

    private static <T> Result<T> result(Boolean success, String errorCode, String errorMessage,
                                        Integer showType, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(success);
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMessage);
        result.setShowType(showType);
        result.setData(data);
        return result;
    }
}