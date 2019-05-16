package com.hex.newbase.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * User: hexuan
 * Date: 2019/4/16
 * Time: 9:43 PM
 */
@Data
public class ChannelVO {
    private String id;

    /**
     * 频道名称
     */
    private String name;

    /**
     * 频道路径
     */
    private String url;

    /**
     * 状态
     */
    private Integer state;

    private String stateStr;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父频道id
     */
    private String parentChannelId;

    /**
     * 父频道名称
     */
    private String parentChannelName;

    /**
     * 子频道集合
     */
    private List<ChannelVO> childChannelVOList;

    /**
     * 在用子频道集合
     */
    private List<ChannelVO> usingChannelVOList;

    /**
     * 创建人
     */
    private OperatorVO creatorVO;

    /**
     * 创建时间
     */
    private Date createTime;
}
