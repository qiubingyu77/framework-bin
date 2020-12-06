package test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiubingyu
 * @date 2020/12/6 22:02
 * @desc
 */
@Api("测试接口文档集合")
@RestController
public class TestController {


    @ApiOperation("测试接口1")
    @GetMapping("/test1")
    public String test1(){
        return "OK";
    }

}
