package com.framework.bin.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @autor qiubingyu
 * @ClassName TestController
 * @date 2020/12/26
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "OK";
    }

}
