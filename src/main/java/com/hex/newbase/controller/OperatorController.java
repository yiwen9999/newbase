package com.hex.newbase.controller;

import com.hex.newbase.converter.Operator2OperatorVOConverter;
import com.hex.newbase.domain.Operator;
import com.hex.newbase.domain.Result;
import com.hex.newbase.domain.Role;
import com.hex.newbase.enums.ResultEnum;
import com.hex.newbase.exception.HexException;
import com.hex.newbase.form.OperatorForm;
import com.hex.newbase.service.OperatorService;
import com.hex.newbase.service.RoleService;
import com.hex.newbase.util.HexUtil;
import com.hex.newbase.util.Md5SaltTool;
import com.hex.newbase.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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
    private RoleService roleService;

    @PostMapping("/login")
    public Result login(String name, String password, HttpSession httpSession) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Operator operator = operatorService.login(name, password);
        if (null != operator) {
//            redisUtil.set(httpSession.getId(), operator);
//            cookieUtil.writeLoginToken(httpServletResponse, httpSession.getId());

            httpSession.setAttribute("operatorInfo", operator);
        }
        return ResultUtil.success(Operator2OperatorVOConverter.converter(operator));
    }

    @GetMapping("/operatorInfo")
    public Result operatorInfo(HttpSession httpSession) {
//        String loginToken = cookieUtil.readLoginToken(httpServletRequest);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ResultUtil.error(101, "用户未登录,无法获取当前用户的信息");
//        }
//        Operator operator = (Operator) redisUtil.get(loginToken);

        Operator operator = (Operator) httpSession.getAttribute("operatorInfo");
        if (null == operator) {
            return ResultUtil.error(101, "用户未登录,无法获取当前用户的信息");
        }

        return ResultUtil.success(Operator2OperatorVOConverter.converter(operator));
    }

    @GetMapping("/logout")
    public Result logout(HttpSession httpSession) {
//        String loginToken = cookieUtil.readLoginToken(httpServletRequest);
//        cookieUtil.delLoginToken(httpServletRequest, httpServletResponse);
//        redisUtil.del(loginToken);

        httpSession.removeAttribute("operatorInfo");
        return ResultUtil.success();
    }

    @PostMapping("/register")
    public Result register(@Valid OperatorForm operatorForm, BindingResult bindingResult) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        HexUtil.validateBindResult(bindingResult);

        Optional<Role> roleOptional = roleService.findById(operatorForm.getRoleId());
        if (!roleOptional.isPresent()) {
            throw new HexException(ResultEnum.ERROR_PARAM);
        }

        Operator operator = new Operator();
        BeanUtils.copyProperties(operatorForm, operator);
        operator.setRole(roleOptional.get());

        operator.setPassword(Md5SaltTool.getEncryptedPwd(operator.getPassword()));

        return ResultUtil.success(Operator2OperatorVOConverter.converter(operatorService.save(operator)));
    }

    @PostMapping("/updatePasswordAdmin")
    public Result updatePasswordAdmin(String operatorId, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Optional<Operator> operatorOptional = operatorService.findById(operatorId);
        Operator operator = null;
        if (operatorOptional.isPresent()) {
            operator = operatorOptional.get();
            operator.setPassword(Md5SaltTool.getEncryptedPwd(password));
            operatorService.save(operator);
        }
        return ResultUtil.success(operator);
    }
}
