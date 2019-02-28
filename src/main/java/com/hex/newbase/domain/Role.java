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
 * 角色
 *
 * @author hexuan
 */
@Entity
@DynamicUpdate
@Data
public class Role implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 50)
    private String id;

    /**
     * 角色名称
     */
    @Column(length = 20)
    private String name;

    /**
     * 说明
     */
    @Column(length = 100)
    private String remark;

    /**
     * 状态
     */
    private Integer state = StateEnum.START.getCode();

    /**
     * 创建人id
     */
    @Column(length = 50)
    private String creatorId;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

}
