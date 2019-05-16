package com.hex.newbase.util;

import com.hex.newbase.domain.Operator;
import com.hex.newbase.enums.ResultEnum;
import com.hex.newbase.exception.HexException;
import com.hex.newbase.vo.OperatorVO;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: hexuan
 * Date: 2017/9/19
 * Time: 上午10:57
 */
public class HexUtil {
    public static Date formatBeginTimeString(String beginTime) {
        Date beginTimeDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (null != beginTime && !StringUtils.isEmpty(beginTime)) {
            try {
                beginTime += " 00:00:00";
                beginTimeDate = simpleDateFormat.parse(beginTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return beginTimeDate;
    }

    public static Date formatEndTimeString(String endTime) {
        Date endTimeDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (null != endTime && !StringUtils.isEmpty(endTime)) {
            try {
                endTime += " 23:59:59";
                endTimeDate = simpleDateFormat.parse(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return endTimeDate;
    }

    public static void validateBindResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new HexException(ResultEnum.ERROR_PARAM.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
    }

    public static Operator getOperator(HttpServletRequest request) {
        Operator operator = null;
        Object object = request.getSession().getAttribute("operatorInfo");
        if (null != object) {
            operator = (Operator) object;
        }
        return operator;
    }

    public static OperatorVO getOperatorVO(HttpServletRequest request) {
        return JwtUtil.parseToken(request.getHeader("Authorization"));
    }

}
