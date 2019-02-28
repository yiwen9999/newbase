package com.hex.newbase.controller;

import com.hex.newbase.domain.Channel;
import com.hex.newbase.domain.Operator;
import com.hex.newbase.domain.Result;
import com.hex.newbase.domain.Role;
import com.hex.newbase.service.ChannelService;
import com.hex.newbase.service.OperatorService;
import com.hex.newbase.service.RoleService;
import com.hex.newbase.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:22 PM
 */
@RestController
public class TestController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OperatorService operatorService;

    @GetMapping("/test")
    public Result test() {
        Operator operator = new Operator();
        Channel channel = new Channel();
        Role role = new Role();

        operator.setName("name");
        operator.setPassword("password");
        operator.setNickname("nickname");
        String operatorId = operatorService.save(operator).getId();

        channel.setCreatorId(operatorId);
        channel.setName("name");
        channel.setSort(1);
        channel.setUrl("url");
        channelService.save(channel).getId();

        role.setName("name");
        role.setRemark("remark");
        role.setCreatorId(operatorId);
        roleService.save(role);

        return ResultUtil.success(operator);
    }
}
