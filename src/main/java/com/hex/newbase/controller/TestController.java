package com.hex.newbase.controller;

import com.hex.newbase.domain.Result;
import com.hex.newbase.util.RedisUtil;
import com.hex.newbase.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:22 PM
 */
@RestController
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public Result test() {
        redisUtil.set("hexKey", "hexValue");
        return ResultUtil.success();
    }
}
