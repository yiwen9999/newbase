package com.hex.newbase.form;

import lombok.Data;

import javax.persistence.Column;

/**
 * User: hexuan
 * Date: 2019/3/18
 * Time: 4:41 PM
 */
@Data
public class OperatorForm {
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;
}
