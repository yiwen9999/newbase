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
 * 操作人员
 *
 * @author hexuan
 */
@Entity
@DynamicUpdate
@Data
public class Operator implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 50)
    private String id;

    /**
     * 用户名
     */
    @Column(nullable = false, length = 40, unique = true)
    private String name;

    /**
     * 昵称
     */
    @Column(nullable = false, length = 40)
    private String nickname;

    /**
     * 密码
     */
    @Column(nullable = false, length = 100)
    private String password;

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

    /**
     * 状态
     */
    private Integer state = StateEnum.START.getCode();

    /**
     * 角色id
     */
    @Column(length = 50)
    private String roleId;

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
