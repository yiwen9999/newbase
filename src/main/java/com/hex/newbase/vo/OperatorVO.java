package com.hex.newbase.vo;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2019/3/26
 * Time: 1:29 PM
 */
@Data
public class OperatorVO {

    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 头像路径
     */
    private String iconPath;

    /**
     * 头像id
     */
    private String iconId;
}
