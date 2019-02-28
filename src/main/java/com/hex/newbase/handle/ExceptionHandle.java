package com.hex.newbase.handle;

import com.hex.newbase.domain.Result;
import com.hex.newbase.enums.ResultEnum;
import com.hex.newbase.exception.HexException;
import com.hex.newbase.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: hexuan
 * Date: 2017/8/14
 * Time: 下午3:04
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        log.error("【系统异常】{}", e);
        if (e instanceof HexException) {
            HexException hexException = (HexException) e;
            return ResultUtil.error(hexException.getCode(), hexException.getMessage());
        } else {
            return ResultUtil.error(ResultEnum.UN_KNOW_ERRO.getCode(), ResultEnum.UN_KNOW_ERRO.getMsg());
        }
    }
}
