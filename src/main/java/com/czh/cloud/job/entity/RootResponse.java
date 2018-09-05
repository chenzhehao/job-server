package com.czh.cloud.job.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/9/5 15:17
 */
@ApiModel(value = "RootResponse对象", description = "RootResponse样例对象")
public class RootResponse<T> {

    @ApiModelProperty(name = "code", value = "返回参数code", example = "000000")
    private String code;

    @ApiModelProperty(name = "message", value = "返回参数message", example = "success")
    private String message;

    @ApiModelProperty(name = "data", value = "返回参数data", example = "data")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
