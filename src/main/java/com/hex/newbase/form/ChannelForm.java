package com.hex.newbase.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * User: hexuan
 * Date: 2019/4/16
 * Time: 9:30 PM
 */
@Data
public class ChannelForm {

    private String id;

    /**
     * 频道名称
     */
    @NotBlank(message = "频道名称不能为空")
    private String name;

    /**
     * 频道路径
     */
    private String url;

    /**
     * 排序
     */
    @NotNull(message = "排序号不能为空")
    private Integer sort;

    /**
     * 父频道id
     */
    private String parentChannelId;
}
