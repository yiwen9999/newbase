package com.hex.newbase.util;

import org.springframework.util.StringUtils;

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

}
