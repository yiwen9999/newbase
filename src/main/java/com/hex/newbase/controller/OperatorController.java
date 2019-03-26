package com.hex.newbase.controller;

import com.hex.newbase.converter.Operator2OperatorVOConverter;
import com.hex.newbase.domain.Operator;
import com.hex.newbase.domain.Result;
import com.hex.newbase.form.OperatorForm;
import com.hex.newbase.service.OperatorService;
import com.hex.newbase.util.CookieUtil;
import com.hex.newbase.util.RedisUtil;
import com.hex.newbase.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * User: hexuan
 * Date: 2019/3/11
 * Time: 2:42 PM
 */
@RestController
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @PostMapping("/login")
    public Result login(String name, String password, HttpSession httpSession, HttpServletResponse httpServletResponse) {
        Operator operator = operatorService.login(name, password);
        if (null != operator) {
            redisUtil.set(httpSession.getId(), operator);
//            stringRedisTemplate.opsForValue().set(httpSession.getId(), JsonUtil.obj2String(operator));
            cookieUtil.writeLoginToken(httpServletResponse, httpSession.getId());
        }
        return ResultUtil.success(Operator2OperatorVOConverter.converter(operator));
    }

    @GetMapping("/operatorInfo")
    public Result operatorInfo(HttpServletRequest httpServletRequest) {
        String loginToken = cookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            return ResultUtil.error(101, "用户未登录,无法获取当前用户的信息");
        }
//        Operator operator = JsonUtil.string2Obj(stringRedisTemplate.opsForValue().get(loginToken), Operator.class);
        Operator operator = (Operator) redisUtil.get(loginToken);
        return ResultUtil.success(Operator2OperatorVOConverter.converter(operator));
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String loginToken = cookieUtil.readLoginToken(httpServletRequest);
        cookieUtil.delLoginToken(httpServletRequest, httpServletResponse);
//        stringRedisTemplate.delete(loginToken);
        redisUtil.del(loginToken);
        return ResultUtil.success();
    }

    @PostMapping("/register")
    public Result register(OperatorForm operatorForm) {

        return ResultUtil.success();
    }
}
