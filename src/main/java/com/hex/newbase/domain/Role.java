package com.hex.newbase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hex.newbase.enums.StateEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * 角色可访问的频道集合
     */
    @ManyToMany
    @JoinTable(name = "channel_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "channel_id"))
    @OrderBy(value = "sort asc")
    @JsonIgnore
    private List<Channel> channelList = new ArrayList<>();

    /**
     * 角色可访问的顶级频道集合
     */
    @ManyToMany
    @JoinTable(name = "channel_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "channel_id"))
    @Where(clause = "parent_id is null")
    @OrderBy(value = "sort asc")
    @JsonIgnore
    private List<Channel> topChannelList = new ArrayList<>();

    /**
     * 角色可访问的子级频道集合
     */
    @ManyToMany
    @JoinTable(name = "channel_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "channel_id"))
    @Where(clause = "parent_id is not null")
    @OrderBy(value = "sort asc")
    @JsonIgnore
    private List<Channel> childChannelList = new ArrayList<>();

    /**
     * 角色可访问的启用频道集合
     */
    @ManyToMany
    @JoinTable(name = "channel_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "channel_id"))
    @Where(clause = "state=2")
    @OrderBy(value = "sort asc")
    @JsonIgnore
    private List<Channel> usingChannelList = new ArrayList<>();

    /**
     * 角色可访问的启用顶级频道集合
     */
    @ManyToMany
    @JoinTable(name = "channel_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "channel_id"))
    @Where(clause = "parent_id is null and state=2")
    @OrderBy(value = "sort asc")
    @JsonIgnore
    private List<Channel> usingTopChannelList = new ArrayList<>();

    /**
     * 角色可访问的启用子级频道集合
     */
    @ManyToMany
    @JoinTable(name = "channel_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "channel_id"))
    @Where(clause = "parent_id is not null and state=2")
    @OrderBy(value = "sort asc")
    @JsonIgnore
    private List<Channel> usingChildChannelList = new ArrayList<>();

    /**
     * 创建人
     */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Operator creator;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

}
