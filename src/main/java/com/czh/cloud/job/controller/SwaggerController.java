package com.czh.cloud.job.controller;

import com.czh.cloud.job.entity.rep.SwaggerReq;
import com.czh.cloud.job.entity.req.SwaggerRep;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhehao.chen
 * @version: V1.0
 * @Description:
 * @date: 2018/9/5 11:16
 */
@RestController
@RequestMapping("/swagger")
@Api(value = "swagger", description = "swagger controller接口样例")
public class SwaggerController {

    @ApiOperation(value = "get接口功能描述", response = SwaggerRep.class)
    @RequestMapping(value = "/v1/swagger/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public SwaggerRep v1Get(@ApiParam(name = "token", value = "用户token值", example = "123", required = true) @RequestHeader(value = "token", required = true) String token,
                            @ApiParam(name = "type", value = "类型 ", example = "1", required = true) @PathVariable(required = true) Integer type,
                            @ApiParam(name = "resultCheckStatus", value = "请求值", example = "123ds", required = true) @RequestParam(required = true) String resultCheckStatus,
                            @ApiParam(name = "swaggerReq", value = "样例对象", required = true) @RequestBody SwaggerReq swaggerReq) {
        return null;// 返回Response
    }

    @ApiOperation(value = "post接口功能描述", response = SwaggerRep.class)
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "用户token值", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "Long", name = "type", value = "类型", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "resultCheckStatus", value = "请求值", required = true)})
    @RequestMapping(value = "/v1/swagger/{type}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public SwaggerRep v1Post(@RequestHeader(value = "token", required = true) String token,
                             @PathVariable(required = true) Integer type,
                             @RequestParam(required = true) String resultCheckStatus,
                             @RequestBody SwaggerReq swaggerReq) {
        return null;// 返回Response
    }
}
