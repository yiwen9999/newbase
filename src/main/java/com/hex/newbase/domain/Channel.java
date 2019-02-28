package com.hex.newbase.domain;

import com.hex.newbase.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 频道
 *
 * @author hexuan
 */
@Entity
@DynamicUpdate
@Data
public class Channel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 50)
    private String id;

    // 频道名称
    @Column(length = 50)
    private String name;

    // 频道路径
    @Column(length = 100)
    private String url;

    /**
     * 状态
     */
    private Integer state = StateEnum.START.getCode();

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父频道id
     */
    @Column(length = 50)
    private String parentId;

    /**
     * 创建人
     */
    @Column(length = 50)
    private String creatorId;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

}
