package com.hex.newbase.controller;

import com.hex.newbase.domain.Result;
import com.hex.newbase.service.ChannelService;
import com.hex.newbase.service.OperatorService;
import com.hex.newbase.service.RoleService;
import com.hex.newbase.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private ChannelService channelService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test")
    public Result test() {
        stringRedisTemplate.opsForValue().set("hexKey", "hexValue");
        return ResultUtil.success();
    }
}
