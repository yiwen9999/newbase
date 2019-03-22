package com.hex.newbase.controller;

import com.hex.newbase.domain.Operator;
import com.hex.newbase.domain.Result;
import com.hex.newbase.form.OperatorForm;
import com.hex.newbase.service.OperatorService;
import com.hex.newbase.util.CookieUtil;
import com.hex.newbase.util.JsonUtil;
import com.hex.newbase.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public Result login(String name, String password, HttpSession httpSession, HttpServletResponse httpServletResponse) {
        Operator operator = operatorService.login(name, password);
        if (null != operator) {
//            redisTemplate.opsForValue().set(httpSession.getId(), JsonUtil.obj2String(operator));

//            ValueOperations<String, Operator> operations = redisTemplate.opsForValue();
//            operations.set(httpSession.getId(), operator);
            stringRedisTemplate.opsForValue().set(httpSession.getId(), JsonUtil.obj2String(operator));
            CookieUtil.writeLoginToken(httpServletResponse, httpSession.getId());
        }
        return ResultUtil.success(JsonUtil.obj2String(operator));
    }

    @GetMapping("/operatorInfo")
    public Result operatorInfo(HttpServletRequest httpServletRequest) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            return ResultUtil.error(101, "用户未登录,无法获取当前用户的信息");
        }
//        ValueOperations<String, Operator> operations = redisTemplate.opsForValue();
//        Operator operator = operations.get(loginToken);

//        Operator operator = JsonUtil.string2Obj((String) redisTemplate.opsForValue().get(loginToken),Operator.class);

        Operator operator = JsonUtil.string2Obj(stringRedisTemplate.opsForValue().get(loginToken), Operator.class);

        return ResultUtil.success(operator);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        CookieUtil.delLoginToken(httpServletRequest, httpServletResponse);
//        redisTemplate.delete(loginToken);
        stringRedisTemplate.delete(loginToken);
        return ResultUtil.success();
    }

    @PostMapping("/addOperator")
    public Result addOperator(OperatorForm operatorForm){

        return ResultUtil.success();
    }
}
